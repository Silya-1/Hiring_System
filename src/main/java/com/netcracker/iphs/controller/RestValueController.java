package com.netcracker.iphs.controller;

import com.netcracker.iphs.database.model.claim.Claim;
import com.netcracker.iphs.database.model.employee.Employee;
import com.netcracker.iphs.database.model.project.Project;
import com.netcracker.iphs.database.model.vacancy.Vacancy;
import com.netcracker.iphs.database.service.claim.ClaimServiceImpl;
import com.netcracker.iphs.database.service.employee.EmployeeServiceImpl;
import com.netcracker.iphs.database.service.project.ProjectServiceImpl;
import com.netcracker.iphs.database.service.vacancy.VacancyServiceImpl;
import com.netcracker.iphs.resources.Updates;
import com.netcracker.iphs.services.serviceImpl.ServiceToSearchForTagsInStringImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/rest")
@CrossOrigin
public class RestValueController {

  private static final Logger log = LoggerFactory.getLogger(RestValueController.class);

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Autowired
  private VacancyServiceImpl vacancyService;

  @Autowired
  public ProjectServiceImpl projectService;

  @Autowired
  public ClaimServiceImpl claimServiceImpl;

  @Autowired
  private ServiceToSearchForTagsInStringImpl serviceToSearchForTagsInString;

  private final List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

  private void sendEvent(Updates updates) {
    synchronized (this.sseEmitters) {
      for (SseEmitter sseEmitter : this.sseEmitters) {
        try {
          sseEmitter.send(updates, MediaType.APPLICATION_JSON);
          sseEmitter.complete();
        } catch (Exception ignored) {
          log.error("", ignored);
        }
      }
    }
  }


  @RequestMapping(value = "/user", method = RequestMethod.GET)
  public String getEmployeeById(@RequestParam Long id) {
    Employee employee = employeeService.getEmployeeById(id);
    employeeService.updateTags(employee);

    return getJSONEmployee(employeeService.getEmployeeById(id)).toString();
  }

  @RequestMapping(value = "/user/login", method = RequestMethod.GET)
  public String getIdByLogin(@RequestParam String login,  String password) {
    Employee employee;
      employee = employeeService.getEmployeeByLogin(login);
      JSONObject jsonObject = new JSONObject();
      if (employee.getPassword().equals(password)) {
        jsonObject.put("TOKEN", employee.getPosition());
        jsonObject.put("id", employee.getId());
        jsonObject.put("name", employee.getName());
      } else {
        jsonObject.put("TOKEN", "");
      }
    return jsonObject.toString();
  }

  @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
  public String updateUserById(@RequestBody String user) {
    Employee employee;
    JSONObject jsonObject = new JSONObject(user);
    JSONArray jsonArrayS = jsonObject.getJSONArray("skills");
    List<String> listSkills = new ArrayList<>();
    if (jsonArrayS != null) {
      int len = jsonArrayS.length();
      for (int i=0;i<len;i++){
        listSkills.add(jsonArrayS.get(i).toString());
      }
    }
    JSONArray jsonArrayAS = jsonObject.getJSONArray("addSkills");
    List<String> listAddSkills = new ArrayList<>();
    if (jsonArrayAS != null) {
      int len = jsonArrayAS.length();
      for (int i=0;i<len;i++){
        listAddSkills.add(jsonArrayAS.get(i).toString());
      }
    }
    JSONArray jsonArrayCon = jsonObject.getJSONArray("conditions");
    List<String> listCon = new ArrayList<>();
    if (jsonArrayCon != null) {
      int len = jsonArrayCon.length();
      for (int i=0;i<len;i++){
        listCon.add(jsonArrayCon.get(i).toString());
      }
    }
//    if (employeeService.existEmployee(jsonObject.getString("name")))
      employee = employeeService.getEmployeeById(jsonObject.getLong("id"));
    employee.setConditions(listCon);
    employee.setSkills(listSkills);
    employee.setAddSkills(listAddSkills);

    employeeService.updateTags(employee);
    return getJSONEmployee(employeeService.getEmployeeById(employee.getId())).toString();
  }

  private JSONObject getJSONEmployee(Employee employee) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", employee.getId());
    jsonObject.put("name", employee.getName());
    jsonObject.put("location", employee.getLocation());
    jsonObject.put("TOKEN", employee.getPosition());
    jsonObject.put("skills", new JSONArray(employee.getSkills()));
    jsonObject.put("addSkills", new JSONArray(employee.getAddSkills()));
    jsonObject.put("conditions", new JSONArray(employee.getConditions()));
    jsonObject.put("tags", new JSONArray(employee.getTags().stream().map(tag -> tag.getValue()).collect(
        Collectors.toList())));
    jsonObject.put("jobRole", employee.getJobRole());
    JSONArray jsonArray = new JSONArray();
    for (Long id : employee.getProjects())
      jsonArray.put(projectService.getProjectById(id).getName());
    jsonObject.put("projects", jsonArray);

    return jsonObject;
  }

  @RequestMapping(value = "/vacancy", method = RequestMethod.GET)
  public Vacancy getVacancy(@RequestParam Long id) {
    return vacancyService.getVacancyById(id);
  }

  @RequestMapping(value = "/vacancy/update", method = RequestMethod.PUT)
  public Vacancy updateVacancy(@RequestBody Vacancy vacancy) {
    Vacancy vacancy1 = vacancyService.saveVacancy(vacancy);
    vacancyService.updateTags(vacancy1);
    vacancy1.setProjectID(projectService.getProjectByName(vacancy1.getProject()));
    projectService.addVacancyToProject(vacancy1);
    return vacancy1;
  }

  @RequestMapping(value = "/claim/update", method = RequestMethod.PUT)
  public Claim updateClaim(@RequestBody Claim claim) {
    Claim claim1 = claimServiceImpl.saveClaim(claim);
    Vacancy vacancy = vacancyService.getVacancyById(claim1.getVacancyID());
    projectService.addClaimToProect(claim1, vacancy);
    return claim1;
  }

  @RequestMapping(value = "/claim/get", method = RequestMethod.GET)
  public String updateClaim(@RequestParam Long projectID) {
    JSONArray jsonOutPut = new JSONArray();
    Project project = projectService.getProjectById(projectID);
    JSONObject jsonObject;
    Employee employee;
    Vacancy vacancy;
    for (Claim c : project.getClaims()) {
      jsonObject = new JSONObject();
      jsonObject.put("userID", c.getEmployeeID());
      employee = employeeService.getEmployeeById(c.getEmployeeID());
      jsonObject.put("userName", employee.getName());
      jsonObject.put("skills", new JSONArray(employee.getSkills()));
      vacancy = vacancyService.getVacancyById(c.getEmployeeID());
      jsonObject.put("vacancyName", vacancy.getName());
      jsonOutPut.put(jsonObject);
    }
    return jsonOutPut.toString();
  }

  @RequestMapping(value = "/projects", method = RequestMethod.GET)
  public List<Project> getAllProjects() {
    return projectService.getAllProjects();
  }

  @RequestMapping(value = "employees", method = RequestMethod.GET)
  public List<Employee> getAllEmployee() {
    return employeeService.listEmployee();
  }


  @RequestMapping(value = "/project", method = RequestMethod.GET)
  public Project getProjectById(@RequestParam Long id) {return projectService.getProjectById(id);
  }


}