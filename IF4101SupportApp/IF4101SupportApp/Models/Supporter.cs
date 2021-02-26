using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace IF4101SupportApp.Models
{
    public class Supporter
    {
        public int EmployeeId { get; set; }
        public string EmployeeName { get; set; }
        public string FirstSurname { get; set; }

        public Supporter()
        {
        }

        public Supporter(int employeeId, string employeeName, string firstSurname)
        {
            EmployeeId = employeeId;
            EmployeeName = employeeName;
            FirstSurname = firstSurname;
        }
    }
}
