using IF4101SupportApp.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using System.Net.Http;
using Newtonsoft.Json;

namespace IF4101SupportApp.Controllers
{
    public class HomeController : Controller
    {
        private readonly IConfiguration _configuration;
        private readonly string apiBaseUrl;

        public HomeController(IConfiguration configuration)
        {
            _configuration = configuration;

            apiBaseUrl = _configuration.GetValue<string>("WebAPISupportBaseUrl");
        }

        public IActionResult Index()
        {
            if (HttpContext.Session.GetInt32("id") != null && HttpContext.Session.GetInt32("id") != -1)
            {
                return View();
            }
            return RedirectToAction("Login", "Home");

        }

        public IActionResult Login()
        {

            return View();


        }

        public async Task<IActionResult> AuthenticationAsync(string email, string password)
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.GetAsync(apiBaseUrl + "Employees/Authentication?email=" + email + "&password=" + password);
            if (Response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                var settings = new JsonSerializerSettings
                {
                    NullValueHandling = NullValueHandling.Ignore,
                    MissingMemberHandling = MissingMemberHandling.Ignore
                };

                var employee = JsonConvert.DeserializeObject<Employee>
                            (await Response.Content.ReadAsStringAsync(), settings);
                HttpContext.Session.SetInt32("role", (int)employee.EmployeeType);
                HttpContext.Session.SetInt32("id", employee.EmployeeId);
                result = Ok(1);



            }
            else if (Response.StatusCode == System.Net.HttpStatusCode.NotFound)
            {
                result = NotFound(null);
            }
            else
            {
                result = Conflict(Response.RequestMessage);
            }
            return result;
        }
        public IActionResult LogOut()
        {

            HttpContext.Session.SetInt32("id", -1);
            return RedirectToAction("Login", "Home");
        }

    }
}
