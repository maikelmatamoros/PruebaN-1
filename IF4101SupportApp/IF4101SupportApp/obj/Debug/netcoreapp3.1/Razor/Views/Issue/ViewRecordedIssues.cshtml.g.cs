#pragma checksum "C:\Users\maike\Desktop\IF4101ProyectoFinal\Desarrollo\IF4101SupportApp\IF4101SupportApp\Views\Issue\ViewRecordedIssues.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "455ab86f6dac47e2075b3467caf11e8937bf3d30"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Issue_ViewRecordedIssues), @"mvc.1.0.view", @"/Views/Issue/ViewRecordedIssues.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\Users\maike\Desktop\IF4101ProyectoFinal\Desarrollo\IF4101SupportApp\IF4101SupportApp\Views\_ViewImports.cshtml"
using IF4101SupportApp;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\Users\maike\Desktop\IF4101ProyectoFinal\Desarrollo\IF4101SupportApp\IF4101SupportApp\Views\_ViewImports.cshtml"
using IF4101SupportApp.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"455ab86f6dac47e2075b3467caf11e8937bf3d30", @"/Views/Issue/ViewRecordedIssues.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"d58c53569a741ed263786808af6aaee47fbcb176", @"/Views/_ViewImports.cshtml")]
    public class Views_Issue_ViewRecordedIssues : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
            WriteLiteral(@"<div class=""container"">
    <div class=""text-center"">
        <h2 class=""section-heading text-uppercase"">Recorded Issues</h2>
        <div class=""table-responsive"">
            <table id=""table-recorded-issues"" class=""table table-striped"" style=""width:100%"">
                <thead>
                    <tr>
                        <th></th>
                        <th>Report number</th>
                        <th>Employee assigned</th>
                        <th>Classification</th>
                        <th>Status</th>
                        <th>Creation date</th>
                        <th>Report Timestamp</th>
                    </tr>
                </thead>
                <tbody class=""tbody""></tbody>
            </table>
        </div>
    </div>
</div>");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591
