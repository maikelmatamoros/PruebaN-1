using IF4101SupportApp.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using WebApiSupport.Models.DTO;

namespace IF4101SupportApp.Controllers
{
    public class IssueController : Controller
    {

        private readonly IConfiguration _configuration;
        private readonly string apiBaseUrl;

        public IActionResult Index()
        {
            return View();
        }

        public IssueController(IConfiguration configuration)
        {
            _configuration = configuration;
            apiBaseUrl = _configuration.GetValue<string>("WebAPISupportBaseUrl");
        }

        public async Task<IActionResult> GetAll()
        {
            ObjectResult objectResult;
            using (var client = new HttpClient())
            {
                using (var Response = await client.GetAsync(apiBaseUrl + "issues/GetIssuesE"))
                {
                    if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                    {
                        string apiResponse = await Response.Content.ReadAsStringAsync();
                        objectResult = Ok(JsonConvert.DeserializeObject<List<Issue>>(apiResponse));
                    }
                    else
                    {
                        ModelState.AddModelError(string.Empty, "Server error. Please contact a administrator");
                        objectResult = Conflict(new List<Issue>());
                    }
                }
            }
            return objectResult;
        }

        public async Task<IActionResult> GetIssuesByIdUser()
        {
            int id = (int)HttpContext.Session.GetInt32("id");
            ObjectResult objectResult;
            using (var client = new HttpClient())
            {
                using (var Response = await client.GetAsync(apiBaseUrl + "Issues / GetIssuesRById/" + id))
                {
                    if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                    {
                        string apiResponse = await Response.Content.ReadAsStringAsync();
                        objectResult = Ok(JsonConvert.DeserializeObject<List<Issue>>(apiResponse));
                    }
                    else
                    {
                        ModelState.AddModelError(string.Empty, "Server error. Please contact a administrator");
                        objectResult = Conflict(new List<Issue>());
                    }
                }
            }
            return objectResult;
        }

        public async Task<IActionResult> GetIssue(int reportNumber)
        {
            ObjectResult objectResult;
            using (var client = new HttpClient())
            {
                using (var Response = await client.GetAsync(apiBaseUrl + "Issues/GetIssuesById/" + reportNumber))
                {
                    if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                    {
                        try
                        {
                            string apiResponse = await Response.Content.ReadAsStringAsync();
                            List<Issue> issues = JsonConvert.DeserializeObject<List<Issue>>(apiResponse);
                            objectResult = Ok(issues.First());

                        }
                        catch (Exception e)
                        {
                            return null;
                        }

                    }
                    else
                    {
                        ModelState.AddModelError(string.Empty, "Server error. Please contact a administrator");
                        objectResult = Conflict(new Issue());
                    }
                }
            }
            return objectResult;
        }

        public async Task<IActionResult> GetSupportersById(int id)
        {
            ObjectResult objectResult;
            using (var client = new HttpClient())
            {
                using (var Response = await client.GetAsync(apiBaseUrl + "employees/GetSupportById/" + id))
                {
                    if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                    {
                        string apiResponse = await Response.Content.ReadAsStringAsync();
                        objectResult = Ok(JsonConvert.DeserializeObject<List<Supporter>>(apiResponse));
                    }
                    else
                    {
                        ModelState.AddModelError(string.Empty, "Server error. Please contact a administrator");
                        objectResult = Conflict(new List<Supporter>());
                    }
                }
            }
            return objectResult;
        }

        public async Task<IActionResult> PutIssue(Issue issue)
        {
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(issue), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PutAsync(apiBaseUrl + "Issues/PutIssue/" + issue.ReportNumber, content))
            {
                if (Response.StatusCode == System.Net.HttpStatusCode.NoContent)
                {
                    result = Ok(1);
                }
                else
                {
                    result = Conflict(Response.RequestMessage);
                }
            }
            return result;
        }

        public async Task<IActionResult> GetReportDataFromClient(int reportNumber)
        {
            ObjectResult objectResult;
            using (var client = new HttpClient())
            {
                using (var Response = await client.GetAsync(apiBaseUrl + "issues/GetReportDataFromClient/" + reportNumber))
                {
                    if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                    {
                        try
                        {
                            string apiResponse = await Response.Content.ReadAsStringAsync();
                            ClientDTO clientDTO = JsonConvert.DeserializeObject<ClientDTO>(apiResponse);
                            objectResult = Ok(clientDTO);

                        }
                        catch (Exception e)
                        {
                            return null;
                        }

                    }
                    else
                    {
                        ModelState.AddModelError(string.Empty, "Server error. Please contact a administrator");
                        objectResult = Conflict(new ClientDTO());
                    }
                }
            }
            return objectResult;
        }

    }
}
