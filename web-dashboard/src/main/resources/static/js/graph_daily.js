window.onload = function () {
    // PO ENROLLED GRAPH
    generateGraph("/graph/generalReport/ENROLLED/egacType/T2MIS/reportSource/PO_REPORT/reportType",
        "poEnrolledGraph", "PO - Enrolled", "Target", "Output");

    // PO GRADUATED GRAPH
    generateGraph("/graph/generalReport/GRADUATED/egacType/T2MIS/reportSource/PO_REPORT/reportType",
        "poGraduatedGraph", "PO - Graduated", "Target", "Output");

    // PO ASSESSED GRAPH
    generateGraph("/graph/generalReport/ASSESSED/egacType/T2MIS/reportSource/PO_REPORT/reportType",
        "poAssessedGraph", "PO - Assessed", "Target", "Output");

    // PO CERTIFIED GRAPH
    generateGraph("/graph/generalReport/CERTIFIED/egacType/T2MIS/reportSource/PO_REPORT/reportType",
        "poCertifiedGraph", "PO - Certified", "Target", "Output");

    // CERTIFICATION RATE GRAPH
    generateGraph("/graph/certificationRate",
        "certificationRateGraph", "Certification Rate", "Assessed", "Certified");

    // RO PER MODE ENROLLED T2 GRAPH
    generateGraph("/graph/roPerMode/ENROLLED/egacType/T2MIS/reportSource",
        "roEnrolledT2Graph", "All Delivery (Enrolled) With EBT (T2MIS)", "Target", "Output");

    // RO PER MODE ENROLLED GS GRAPH
    generateGraph("/graph/roPerMode/ENROLLED/egacType/GS/reportSource",
        "roEnrolledGSGraph", "All Delivery (Enrolled) With EBT (Google Sheet)", "Target", "Output");

    // RO PER MODE GRADUATED T2 GRAPH
    generateGraph("/graph/roPerMode/GRADUATED/egacType/T2MIS/reportSource",
        "roGraduatedT2Graph", "All Delivery (Graduates) With EBT (T2MIS)", "Target", "Output");

    // RO PER MODE GRADUATED GS GRAPH
    generateGraph("/graph/roPerMode/GRADUATED/egacType/GS/reportSource",
        "roGraduatedGSGraph", "All Delivery (Graduates) With EBT (Google Sheet)", "Target", "Output");

    // INSTITUTION BASED ENROLLED
    generateGraph("/graph/generalReport/ENROLLED/egacType/T2MIS/reportSource/INSTITUTION_BASED_REPORT/reportType",
        "institutionEnrolledGraph", "Institution-Based (Enrolled)", "Target", "Output");

    // INSTITUTION BASED GRADUATED
    generateGraph("/graph/generalReport/GRADUATED/egacType/T2MIS/reportSource/INSTITUTION_BASED_REPORT/reportType",
        "institutionGraduatedGraph", "Institution-Based (Graduates)", "Target", "Output");

    // ENTERPRISE BASED ENROLLED T2
    generateGraph("/graph/generalReport/ENROLLED/egacType/T2MIS/reportSource/ENTERPRISE_BASED_REPORT/reportType",
        "enterpriseEnrolledT2Graph", "Enterprise-Based (Enrolled) T2MIS", "Target", "Output");

    // ENTERPRISE BASED GRADUATED T2
    generateGraph("/graph/generalReport/GRADUATED/egacType/T2MIS/reportSource/ENTERPRISE_BASED_REPORT/reportType",
        "enterpriseGraduatedT2Graph", "Enterprise-Based (Graduates) T2MIS", "Target", "Output");

    // ENTERPRISE BASED ENROLLED GS
    generateGraph("/graph/generalReport/ENROLLED/egacType/GS/reportSource/ENTERPRISE_BASED_REPORT/reportType",
        "enterpriseEnrolledGSGraph", "Enterprise-Based (Enrolled) Google Sheet", "Target", "Output");

    // ENTERPRISE BASED GRADUATED GS
    generateGraph("/graph/generalReport/GRADUATED/egacType/GS/reportSource/ENTERPRISE_BASED_REPORT/reportType",
        "enterpriseGraduatedGSGraph", "Enterprise-Based (Graduates) Google Sheet", "Target", "Output");

    // COMMUNITY BASED ENROLLED
    generateGraph("/graph/generalReport/ENROLLED/egacType/T2MIS/reportSource/COMMUNITY_BASED_REPORT/reportType",
        "communityEnrolledGraph", "Community-Based (Enrolled)", "Target", "Output");

    // COMMUNITY BASED GRADUATED
    generateGraph("/graph/generalReport/GRADUATED/egacType/T2MIS/reportSource/COMMUNITY_BASED_REPORT/reportType",
        "communityGraduatedGraph", "Community-Based (Graduates)", "Target", "Output");

    // TTI ENROLLED GRAPH
    generateGraph("/graph/generalReport/ENROLLED/egacType/T2MIS/reportSource/TTI_REPORT/reportType",
        "ttiEnrolledGraph", "Enrolled per TTI", "Target", "Output");

    // TTI GRADUATED GRAPH
    generateGraph("/graph/generalReport/GRADUATED/egacType/T2MIS/reportSource/TTI_REPORT/reportType",
        "ttiGraduatedGraph", "Graduated per TTI", "Target", "Output");

    // TTI ASSESSED GRAPH
    generateGraph("/graph/ttiReport/ASSESSED/egacType",
        "ttiAssessedGraph", "Assessed per TTI", "Target", "Output");

    // TTI CERTIFIED GRAPH
    generateGraph("/graph/ttiReport/CERTIFIED/egacType",
        "ttiCertifiedGraph", "Certified per TTI", "Target", "Output");
}

function generateGraph(urlTarget, chartName, title, legend1, legend2) {
    var dataPointsTarget = [];
    var dataPointsOutput = [];
    var dataPointsRate = [];
    var chart = new CanvasJS.Chart(chartName, {
        animationEnabled: true,
        title:{
            text:title
        },
        axisY: {
            lineColor: "#369EAD",
            titleFontColor: "#369EAD",
            labelFontColor: "#369EAD"
        },
        axisY2:{
            title: "Percent (%)",
            lineColor: "#7F6084",
            titleFontColor: "#7F6084",
            labelFontColor: "#7F6084"
        },
        data: [{
            type: "column",
            name: legend1,
            indexLabel: "{y}",
            indexLabelFontSize: 12,
            showInLegend: true,
            dataPoints : dataPointsTarget,
        },
        {
            type: "column",
            name: legend2,
            indexLabel: "{y}",
            indexLabelFontSize: 12,
            showInLegend: true,
            dataPoints : dataPointsOutput,
        },
        {
            type: "line",
            name: "Rate",
            axisYType: "secondary",
            indexLabel: "{y}",
            indexLabelFormatter: function(e){
                return e.dataPoint.y + " " + "%" ;
            },
            indexLabelFontSize: 12,
            showInLegend: true,
            dataPoints : dataPointsRate,
        }
        ]
    });
    $.getJSON(urlTarget, function(data) {
        var targetData = data.targetData;
        for (var i = 0; i < targetData.dataPoints.length; i++) {
            dataPointsTarget.push({
                label:targetData.dataPoints[i].label,
                y:targetData.dataPoints[i].value
            });
        }
        var outputData = data.outputData;
        for (var i = 0; i < outputData.dataPoints.length; i++) {
            dataPointsOutput.push({
                label:outputData.dataPoints[i].label,
                y:outputData.dataPoints[i].value
            });
        }
        var rateData = data.rateData;
        for (var i = 0; i < rateData.dataPoints.length; i++) {
            dataPointsRate.push({
                label:rateData.dataPoints[i].label,
                y:rateData.dataPoints[i].value
            });
        }
        chart.render();
    });
}

$("#downloadGraphButton").click(function(){
    var HTML_Width = $(".canvas_div_pdf").width();
    var HTML_Height = $(".canvas_div_pdf").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.5);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;

    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;


    html2canvas($(".canvas_div_pdf")[0],{allowTaint:true}).then(function(canvas) {
        canvas.getContext('2d');

        console.log(canvas.height+"  "+canvas.width);


        var imgData = canvas.toDataURL("image/jpeg", 1.0);
        var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
        pdf.addImage(imgData, 'JPG', top_left_margin, 0,canvas_image_width,canvas_image_height);


        for (var i = 1; i <= totalPDFPages; i++) {
            pdf.addPage(PDF_Width, PDF_Height);
            pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
        }

        pdf.save("TESDA-Daily-Accomplishment-Reports.pdf");
    });
});
