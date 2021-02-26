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

namespace IF4101SupportApp.Controllers
{
    public class EmployeeController : Controller
    {

        private readonly IConfiguration _configuration;
        private readonly string apiBaseUrl;
        public EmployeeController(IConfiguration configuration)
        {
            _configuration = configuration;

            apiBaseUrl = _configuration.GetValue<string>("WebAPISupportBaseUrl");
           
        }

        [HttpPost]
        public async Task<IActionResult> Insert(Employee employee)
        {
            employee.CreatedBy = (int)HttpContext.Session.GetInt32("id");
            employee.Supervised = (int)HttpContext.Session.GetInt32("id");
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(employee), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PostAsync(apiBaseUrl + "Employees", content))
            {
                if (Response.StatusCode == System.Net.HttpStatusCode.OK)
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

        [HttpPut]
        public async Task<IActionResult> Update(Employee employee)
        {
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(employee), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PutAsync(apiBaseUrl + "Employees", content))
            {
                if (Response.StatusCode == System.Net.HttpStatusCode.OK)
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

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Employee>>> GetAllAsync()
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.GetAsync(apiBaseUrl + "Employees");
            if (Response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                result = Ok(JsonConvert.DeserializeObject<List<Employee>>
                    (await Response.Content.ReadAsStringAsync()));
            }
            else
            {
                result = Conflict(Response.RequestMessage);
            }
            return result;

        }

        [HttpDelete]
        public async Task<ActionResult> Delete(int Id)
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.DeleteAsync(apiBaseUrl + "Employees/"+ Id);
            if (Response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                result = Ok(1);
            }
            else
            {
                result = Conflict(Response.RequestMessage);
            }
            return result;

        }

        [HttpGet]
        public async Task<ActionResult<Employee>> GetById(int id)
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.GetAsync(apiBaseUrl + "Employees/"+id);
            if (Response.StatusCode == System.Net.HttpStatusCode.OK)
            {
                result = Ok(JsonConvert.DeserializeObject<Employee>
                    (await Response.Content.ReadAsStringAsync()));
            }
            else
            {
                result = Conflict(Response.RequestMessage);
            }
            return result;

        }


    }
}
