using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IF4101SupportApp.Models
{
    public class Service
    {
        public int ServiceId { get; set; }
        public string Name { get; set; }
        public bool? State { get; set; }
        public DateTime? CreationDate { get; set; }
        public DateTime? ModifyDate { get; set; }
        public int? CreatedBy { get; set; }
        public int? ModifiedBy { get; set; }

        //public virtual ICollection<EmployeeService> EmployeeServices { get; set; }
        //public virtual ICollection<Issue> Issues { get; set; }
    }
}
