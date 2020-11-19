window.onload = function () {

    // Monthly Leyte Enrolled
    generateGraph("/graph/monthlyReports/LEYTE/operatingUnitType/ENROLLED/egacType",
        "leyteEnrolledGraph", "Comparative Report of Enrolled in Leyte", "Target", "Total");

    // Monthly Leyte Graduated
    generateGraph("/graph/monthlyReports/LEYTE/operatingUnitType/GRADUATED/egacType",
        "leyteGraduatedGraph", "Comparative Report of Graduated in Leyte", "Target", "Total");

    // Monthly Leyte Assessed
    generateGraph("/graph/monthlyReports/LEYTE/operatingUnitType/ASSESSED/egacType",
        "leyteAssessedGraph", "Comparative Report of Assessed in Leyte", "Target", "Total");

    // Monthly Leyte Certified
    generateGraph("/graph/monthlyReports/LEYTE/operatingUnitType/CERTIFIED/egacType",
        "leyteCertifiedGraph", "Comparative Report of Certified in Leyte", "Target", "Total");



    // Monthly Southern Leyte Enrolled
    generateGraph("/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/ENROLLED/egacType",
        "southernLeyteEnrolledGraph", "Comparative Report of Enrolled in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Graduated
    generateGraph("/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/GRADUATED/egacType",
        "southernLeyteGraduatedGraph", "Comparative Report of Graduated in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Assessed
    generateGraph("/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/ASSESSED/egacType",
        "southernLeyteAssessedGraph", "Comparative Report of Assessed in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Certified
    generateGraph("/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/CERTIFIED/egacType",
        "southernLeyteCertifiedGraph", "Comparative Report of Certified in Southern Leyte", "Target", "Total");


    // Monthly Biliran Enrolled
    generateGraph("/graph/monthlyReports/BILIRAN/operatingUnitType/ENROLLED/egacType",
        "biliranEnrolledGraph", "Comparative Report of Enrolled in Biliran", "Target", "Total");

    // Monthly Biliran Graduated
    generateGraph("/graph/monthlyReports/BILIRAN/operatingUnitType/GRADUATED/egacType",
        "biliranGraduatedGraph", "Comparative Report of Graduated in Biliran", "Target", "Total");

    // Monthly Biliran Assessed
    generateGraph("/graph/monthlyReports/BILIRAN/operatingUnitType/ASSESSED/egacType",
        "biliranAssessedGraph", "Comparative Report of Assessed in Biliran", "Target", "Total");

    // Monthly Biliran Certified
    generateGraph("/graph/monthlyReports/BILIRAN/operatingUnitType/CERTIFIED/egacType",
        "biliranCertifiedGraph", "Comparative Report of Certified in Biliran", "Target", "Total");


    // Monthly Samar Enrolled
    generateGraph("/graph/monthlyReports/SAMAR/operatingUnitType/ENROLLED/egacType",
        "samarEnrolledGraph", "Comparative Report of Enrolled in Samar", "Target", "Total");

    // Monthly Samar Graduated
    generateGraph("/graph/monthlyReports/SAMAR/operatingUnitType/GRADUATED/egacType",
        "samarGraduatedGraph", "Comparative Report of Graduated in Samar", "Target", "Total");

    // Monthly Samar Assessed
    generateGraph("/graph/monthlyReports/SAMAR/operatingUnitType/ASSESSED/egacType",
        "samarAssessedGraph", "Comparative Report of Assessed in Samar", "Target", "Total");

    // Monthly Samar Certified
    generateGraph("/graph/monthlyReports/SAMAR/operatingUnitType/CERTIFIED/egacType",
        "samarCertifiedGraph", "Comparative Report of Certified in Samar", "Target", "Total");


    // Monthly Eastern Samar Enrolled
    generateGraph("/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/ENROLLED/egacType",
        "easternSamarEnrolledGraph", "Comparative Report of Enrolled in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Graduated
    generateGraph("/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/GRADUATED/egacType",
        "easternSamarGraduatedGraph", "Comparative Report of Graduated in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Assessed
    generateGraph("/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/ASSESSED/egacType",
        "easternSamarAssessedGraph", "Comparative Report of Assessed in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Certified
    generateGraph("/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/CERTIFIED/egacType",
        "easternSamarCertifiedGraph", "Comparative Report of Certified in Eastern Samar", "Target", "Total");


    // Monthly Northern Samar Enrolled
    generateGraph("/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/ENROLLED/egacType",
        "northernSamarEnrolledGraph", "Comparative Report of Enrolled in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Graduated
    generateGraph("/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/GRADUATED/egacType",
        "northernSamarGraduatedGraph", "Comparative Report of Graduated in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Assessed
    generateGraph("/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/ASSESSED/egacType",
        "northernSamarAssessedGraph", "Comparative Report of Assessed in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Certified
    generateGraph("/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/CERTIFIED/egacType",
        "northernSamarCertifiedGraph", "Comparative Report of Certified in Northern Samar", "Target", "Total");


    // Monthly Region Enrolled
    generateGraph("/graph/monthlyReports/TOTAL/operatingUnitType/ENROLLED/egacType",
        "regionEnrolledGraph", "Comparative Report of Enrolled in Region VIII", "Target", "Total");

    // Monthly Region Graduated
    generateGraph("/graph/monthlyReports/TOTAL/operatingUnitType/GRADUATED/egacType",
        "regionGraduatedGraph", "Comparative Report of Graduated in Region VIII", "Target", "Total");

    // Monthly Region Assessed
    generateGraph("/graph/monthlyReports/TOTAL/operatingUnitType/ASSESSED/egacType",
        "regionAssessedGraph", "Comparative Report of Assessed in Region VIII", "Target", "Total");

    // Monthly Region Certified
    generateGraph("/graph/monthlyReports/TOTAL/operatingUnitType/CERTIFIED/egacType",
        "regionCertifiedGraph", "Comparative Report of Certified in Region VIII", "Target", "Total");
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
                indexLabelFormatter: function(e){
                    return e.dataPoint.y + " " + "%" ;
                },
                axisYType: "secondary",
                indexLabel: "{y}",
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
    swal("Exporting Data as PDF.", "Your report is being downloaded in a moment.", "success");
    var HTML_Width = $(".canvas_div_pdf").width();
    var HTML_Height = $(".canvas_div_pdf").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;

    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;

    var papGroupTypeLabel = $('#papGroupTypeLabel').val();
    setTimeout(function () {
        html2canvas($(".canvas_div_pdf")[0],{allowTaint:true}).then(function(canvas) {
            canvas.getContext('2d');

            console.log(canvas.height+"  "+canvas.width);


            var imgData = canvas.toDataURL("image/jpeg", 1.0);
            var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin,canvas_image_width,canvas_image_height);


            for (var i = 1; i <= totalPDFPages; i++) {
                var offset;
                if (i === 3) {
                    offset = 60;
                } else if (i > 3) {
                    offset += 30;
                } else {
                    offset = 0;
                }
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4) + offset,canvas_image_width,canvas_image_height);
            }

            pdf.save("TESDA-Monthly-Comparative-Reports.pdf");
        });
    }, 3000);
});
