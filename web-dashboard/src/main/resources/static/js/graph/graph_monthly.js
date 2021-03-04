window.onload = function () {
    var yearValue = $('#yearValue').val();

    // Monthly Leyte Enrolled
    generateGraph("/api/graph/monthlyReports/LEYTE/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "leyteEnrolledGraph", "Comparative Report of Enrolled in Leyte", "Target", "Total");

    // Monthly Leyte Graduated
    generateGraph("/api/graph/monthlyReports/LEYTE/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "leyteGraduatedGraph", "Comparative Report of Graduated in Leyte", "Target", "Total");

    // Monthly Leyte Assessed
    generateGraph("/api/graph/monthlyReports/LEYTE/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "leyteAssessedGraph", "Comparative Report of Assessed in Leyte", "Target", "Total");

    // Monthly Leyte Certified
    generateGraph("/api/graph/monthlyReports/LEYTE/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "leyteCertifiedGraph", "Comparative Report of Certified in Leyte", "Target", "Total");



    // Monthly Southern Leyte Enrolled
    generateGraph("/api/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "southernLeyteEnrolledGraph", "Comparative Report of Enrolled in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Graduated
    generateGraph("/api/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "southernLeyteGraduatedGraph", "Comparative Report of Graduated in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Assessed
    generateGraph("/api/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "southernLeyteAssessedGraph", "Comparative Report of Assessed in Southern Leyte", "Target", "Total");

    // Monthly Southern Leyte Certified
    generateGraph("/api/graph/monthlyReports/SOUTHERN_LEYTE/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "southernLeyteCertifiedGraph", "Comparative Report of Certified in Southern Leyte", "Target", "Total");


    // Monthly Biliran Enrolled
    generateGraph("/api/graph/monthlyReports/BILIRAN/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "biliranEnrolledGraph", "Comparative Report of Enrolled in Biliran", "Target", "Total");

    // Monthly Biliran Graduated
    generateGraph("/api/graph/monthlyReports/BILIRAN/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "biliranGraduatedGraph", "Comparative Report of Graduated in Biliran", "Target", "Total");

    // Monthly Biliran Assessed
    generateGraph("/api/graph/monthlyReports/BILIRAN/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "biliranAssessedGraph", "Comparative Report of Assessed in Biliran", "Target", "Total");

    // Monthly Biliran Certified
    generateGraph("/api/graph/monthlyReports/BILIRAN/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "biliranCertifiedGraph", "Comparative Report of Certified in Biliran", "Target", "Total");


    // Monthly Samar Enrolled
    generateGraph("/api/graph/monthlyReports/SAMAR/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "samarEnrolledGraph", "Comparative Report of Enrolled in Samar", "Target", "Total");

    // Monthly Samar Graduated
    generateGraph("/api/graph/monthlyReports/SAMAR/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "samarGraduatedGraph", "Comparative Report of Graduated in Samar", "Target", "Total");

    // Monthly Samar Assessed
    generateGraph("/api/graph/monthlyReports/SAMAR/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "samarAssessedGraph", "Comparative Report of Assessed in Samar", "Target", "Total");

    // Monthly Samar Certified
    generateGraph("/api/graph/monthlyReports/SAMAR/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "samarCertifiedGraph", "Comparative Report of Certified in Samar", "Target", "Total");


    // Monthly Eastern Samar Enrolled
    generateGraph("/api/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "easternSamarEnrolledGraph", "Comparative Report of Enrolled in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Graduated
    generateGraph("/api/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "easternSamarGraduatedGraph", "Comparative Report of Graduated in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Assessed
    generateGraph("/api/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "easternSamarAssessedGraph", "Comparative Report of Assessed in Eastern Samar", "Target", "Total");

    // Monthly Eastern Samar Certified
    generateGraph("/api/graph/monthlyReports/EASTERN_SAMAR/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "easternSamarCertifiedGraph", "Comparative Report of Certified in Eastern Samar", "Target", "Total");


    // Monthly Northern Samar Enrolled
    generateGraph("/api/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "northernSamarEnrolledGraph", "Comparative Report of Enrolled in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Graduated
    generateGraph("/api/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "northernSamarGraduatedGraph", "Comparative Report of Graduated in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Assessed
    generateGraph("/api/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "northernSamarAssessedGraph", "Comparative Report of Assessed in Northern Samar", "Target", "Total");

    // Monthly Northern Samar Certified
    generateGraph("/api/graph/monthlyReports/NORTHERN_SAMAR/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "northernSamarCertifiedGraph", "Comparative Report of Certified in Northern Samar", "Target", "Total");


    // Monthly Region Enrolled
    generateGraph("/api/graph/monthlyReports/TOTAL/operatingUnitType/ENROLLED/egacType/" + yearValue + "/year",
        "regionEnrolledGraph", "Comparative Report of Enrolled in Region VIII", "Target", "Total");

    // Monthly Region Graduated
    generateGraph("/api/graph/monthlyReports/TOTAL/operatingUnitType/GRADUATED/egacType/" + yearValue + "/year",
        "regionGraduatedGraph", "Comparative Report of Graduated in Region VIII", "Target", "Total");

    // Monthly Region Assessed
    generateGraph("/api/graph/monthlyReports/TOTAL/operatingUnitType/ASSESSED/egacType/" + yearValue + "/year",
        "regionAssessedGraph", "Comparative Report of Assessed in Region VIII", "Target", "Total");

    // Monthly Region Certified
    generateGraph("/api/graph/monthlyReports/TOTAL/operatingUnitType/CERTIFIED/egacType/" + yearValue + "/year",
        "regionCertifiedGraph", "Comparative Report of Certified in Region VIII", "Target", "Total");
}

function generateGraph(urlTarget, chartName, title, legend1, legend2) {
    var dataPointsTarget = [];
    var dataPointsOutput = [];
    var dataPointsRate = [];
    var chart = new CanvasJS.Chart(chartName, {
        animationEnabled: true,
        zoomEnabled: true,
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
            minimum: -100,
            titleFontColor: "#7F6084",
            labelFontColor: "#7F6084"
        },
        data: [{
            type: "column",
            name: legend1,
            color: "#4978FF",
            indexLabelFontColor: "#black",
            indexLabel: "{y}",
            indexLabelFontSize: 12,
            showInLegend: true,
            dataPoints : dataPointsTarget,
        },
        {
            type: "column",
            name: legend2,
            color: "#FF4972",
            indexLabelFontColor: "black",
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
            color: "#A00000",
            indexLabelFontColor: "#A00000",
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
    window.scrollTo(0, 0);
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
