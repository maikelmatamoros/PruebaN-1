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
    public class CommentController : Controller
    {
        private readonly IConfiguration _configuration;
        private readonly string apiBaseUrl;
        public CommentController(IConfiguration configuration)
        {
            _configuration = configuration;

            apiBaseUrl = _configuration.GetValue<string>("WebAPISupportBaseUrl");

        }

        [HttpGet]
        public async Task<ActionResult> CommentsByIssue(int id)
        {
            try
            {
                List<Comment> commentList = new List<Comment>();
                using var client = new HttpClient();
                using var Response = await client.GetAsync(apiBaseUrl + "Comment/GetByReport/" + id);
                commentList = JsonConvert.DeserializeObject<List<Comment>>
                        (await Response.Content.ReadAsStringAsync());

                return PartialView("_ListComment", commentList);
            }
            catch (Exception e)
            {
                return Conflict(e.Message);
            }
        }

        [HttpPost]
        public async Task<IActionResult> Insert(Comment comment)
        {
            comment.Created_By = (int)HttpContext.Session.GetInt32("id");
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(comment), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PostAsync(apiBaseUrl + "Comment/PostComment", content))
            {
                if (Response.StatusCode == System.Net.HttpStatusCode.OK)
                {
                    return PartialView("_CommentCard", JsonConvert.DeserializeObject<Comment>
                    (await Response.Content.ReadAsStringAsync()));
                }
                else
                {
                    result = Conflict(Response.RequestMessage);
                }
            }
            return result;
        }

        [HttpDelete]
        public async Task<ActionResult> Delete(int Id)
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.DeleteAsync(apiBaseUrl + "Comment/Delete/" + Id);
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

        [HttpPut]
        public async Task<IActionResult> Update(Comment comment)
        {

            comment.Modified_By = (int)HttpContext.Session.GetInt32("id");

            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(comment), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PutAsync(apiBaseUrl + "Comment/Update", content))
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


    }
}
