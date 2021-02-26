using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IF4101SupportApp.Models
{
    public class Comment
    {
        public int Comment_Id { get; set; }
        public string Description { get; set; }
        public DateTime? Comment_Timestamp { get; set; }
        public int Report_Number { get; set; }
        public bool State { get; set; }
        public DateTime? Creation_Date { get; set; }
        public DateTime? Modify_Date { get; set; }
        public int? Created_By { get; set; }
        public int? Modified_By { get; set; }

        public Comment()
        {

        }
    }
}
