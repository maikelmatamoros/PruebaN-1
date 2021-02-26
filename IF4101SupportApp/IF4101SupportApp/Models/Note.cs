using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IF4101SupportApp.Models
{
    public class Note
    {
        public int NoteId { get; set; }
        public string Description { get; set; }
        public DateTime? NoteTimestamp { get; set; }
        public int? ReportNumber { get; set; }
        public int? CreatedBy { get; set; }
        public int? ModifiedBy { get; set; }

        public Note()
        {

        }
    }
}
