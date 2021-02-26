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
    public class NoteController : Controller
    {
        private readonly IConfiguration _configuration;
        private readonly string apiBaseUrl;
        public NoteController(IConfiguration configuration)
        {
            _configuration = configuration;

            apiBaseUrl = _configuration.GetValue<string>("WebAPISupportBaseUrl");
            
        }



        [HttpGet]
        public async Task<ActionResult> NotesByIssue(int id)
        {       
            try
            {
                List<Note> noteList = new List<Note>();
                using var client = new HttpClient();
                using var Response = await client.GetAsync(apiBaseUrl + "Notes/GetNotes/"+id);
                noteList = JsonConvert.DeserializeObject<List<Note>>
                        (await Response.Content.ReadAsStringAsync());
                
                return PartialView("_ListCard", noteList);
            }
            catch (Exception e)
            {
                return Conflict(e.Message);
            }
        }

        [HttpPost]
        public async Task<IActionResult> Insert(Note note)
        {
            note.CreatedBy = (int)HttpContext.Session.GetInt32("id");
            note.NoteTimestamp = DateTime.Now;
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(note), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PostAsync(apiBaseUrl + "Notes/PostNote", content))
            {
                if (Response.StatusCode == System.Net.HttpStatusCode.Created)
                {
                    return PartialView("_NoteCard", JsonConvert.DeserializeObject<Note>
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
        public async Task<ActionResult> Delete(int NoteId)
        {
            ObjectResult result = null;
            using var client = new HttpClient();
            using var Response = await client.DeleteAsync(apiBaseUrl + "Notes/" + NoteId);
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
        public async Task<IActionResult> Update(Note note)
        {
            note.ModifiedBy = (int)HttpContext.Session.GetInt32("id");
            ObjectResult result = null;
            using HttpClient client = new HttpClient();
            StringContent content = new StringContent(JsonConvert.SerializeObject(note), Encoding.UTF8,
                "application/json");
            using (var Response = await client.PutAsync(apiBaseUrl + "Notes/PutNote", content))
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
