using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace IF4101SupportApp.Models
{
    public class Employee
    {
        public int EmployeeId { get; set; }
        public string EmployeeName { get; set; }
        public string FirstSurname { get; set; }
        public string SecondSurname { get; set; }
        
        public string Email { get; set; }
        public string Password { get; set; }
        public int? Supervised { get; set; }

        public int? EmployeeType { get; set; }
        public int? CreatedBy { get; set; }
        public List<int> Services { get; set; }

        public Employee(string employeeName, string firstSurname, string secondSurname, string email, string password, int supervised, List<int> services)
        {
            EmployeeName = employeeName;
            FirstSurname = firstSurname;
            SecondSurname = secondSurname;
            Email = email;
            Password = password;
            Supervised = supervised;
            Services = services;
        }

        public Employee()
        {
                        
        }
    }
}
