window.onload = function () {
    // PO ENROLLED GRAPH
    var requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'PO_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "poEnrolledGraph", "Enrolled per PO", "Target", "Output");

    // PO GRADUATED GRAPH
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'PO_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "poGraduatedGraph", "Graduated per PO", "Target", "Output");

    // PO ASSESSED GRAPH
    requestParameters = {
        egacType: 'ASSESSED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'PO_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "poAssessedGraph", "Assessed per PO", "Target", "Output");

    // PO CERTIFIED GRAPH
    requestParameters = {
        egacType: 'CERTIFIED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'PO_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "poCertifiedGraph", "Certified per PO", "Target", "Output");

    // CERTIFICATION RATE GRAPH
    generateGraph("/api/graph/certificationRate", null,
        "certificationRateGraph", "Certification Rate per PO", "Assessed", "Certified");

    // RO PER MODE ENROLLED T2 GRAPH
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'T2MIS'
    }
    generateGraph("/api/graph/roPerMode", requestParameters,
        "roEnrolledT2Graph", "All Delivery (Enrolled) With EBT (T2MIS)", "Target", "Output");

    // RO PER MODE ENROLLED GS GRAPH
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'GS'
    }
    generateGraph("/api/graph/roPerMode", requestParameters,
        "roEnrolledGSGraph", "All Delivery (Enrolled) With EBT (Google Sheet)", "Target", "Output");

    // RO PER MODE GRADUATED T2 GRAPH
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'T2MIS'
    }
    generateGraph("/api/graph/roPerMode", requestParameters,
        "roGraduatedT2Graph", "All Delivery (Graduates) With EBT (T2MIS)", "Target", "Output");

    // RO PER MODE GRADUATED GS GRAPH
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'GS'
    }
    generateGraph("/api/graph/roPerMode", requestParameters,
        "roGraduatedGSGraph", "All Delivery (Graduates) With EBT (Google Sheet)", "Target", "Output");

    // INSTITUTION BASED ENROLLED
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'INSTITUTION_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "institutionEnrolledGraph", "Institution-Based (Enrolled)", "Target", "Output");

    // INSTITUTION BASED GRADUATED
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'INSTITUTION_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "institutionGraduatedGraph", "Institution-Based (Graduates)", "Target", "Output");

    // ENTERPRISE BASED ENROLLED T2
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'ENTERPRISE_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "enterpriseEnrolledT2Graph", "Enterprise-Based (Enrolled) T2MIS", "Target", "Output");

    // ENTERPRISE BASED GRADUATED T2
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'ENTERPRISE_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "enterpriseGraduatedT2Graph", "Enterprise-Based (Graduates) T2MIS", "Target", "Output");

    // ENTERPRISE BASED ENROLLED GS
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'GS',
        dailyReportType: 'ENTERPRISE_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "enterpriseEnrolledGSGraph", "Enterprise-Based (Enrolled) Google Sheet", "Target", "Output");

    // ENTERPRISE BASED GRADUATED GS
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'GS',
        dailyReportType: 'ENTERPRISE_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "enterpriseGraduatedGSGraph", "Enterprise-Based (Graduates) Google Sheet", "Target", "Output");

    // COMMUNITY BASED ENROLLED
    requestParameters = {
        egacType: 'ENROLLED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'COMMUNITY_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "communityEnrolledGraph", "Community-Based (Enrolled)", "Target", "Output");

    // COMMUNITY BASED GRADUATED
    requestParameters = {
        egacType: 'GRADUATED',
        reportSourceType: 'T2MIS',
        dailyReportType: 'COMMUNITY_BASED_REPORT'
    }
    generateGraph("/api/graph/generalReport", requestParameters,
        "communityGraduatedGraph", "Community-Based (Graduates)", "Target", "Output");

    // TTI ENROLLED GRAPH
    requestParameters = {
        egacType: 'ENROLLED'
    }
    generateGraph("/api/graph/ttiReport", requestParameters,
        "ttiEnrolledGraph", "Enrolled per TTI", "Target", "Output");

    // TTI GRADUATED GRAPH
    requestParameters = {
        egacType: 'GRADUATED'
    }
    generateGraph("/api/graph/ttiReport", requestParameters,
        "ttiGraduatedGraph", "Graduated per TTI", "Target", "Output");

    // TTI ASSESSED GRAPH
    requestParameters = {
        egacType: 'ASSESSED'
    }
    generateGraph("/api/graph/ttiReport", requestParameters,
        "ttiAssessedGraph", "Assessed per TTI", "Target", "Output");

    // TTI CERTIFIED GRAPH
    requestParameters = {
        egacType: 'CERTIFIED'
    }
    generateGraph("/api/graph/ttiReport", requestParameters,
        "ttiCertifiedGraph", "Certified per TTI", "Target", "Output");
}

function generateGraph(urlTarget, requestParameters, chartName, title, legend1, legend2) {
    var dataPointsTarget = [];
    var dataPointsOutput = [];
    var dataPointsRate = [];
    var chart = new CanvasJS.Chart(chartName, {
        animationEnabled: true,
        title:{
            text:title
        },
        axisX: {
            interval: chartName.includes("tti") ? 1 : 0,
            labelAngle: chartName.includes("tti") ? -70 : 0
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
    $.getJSON(urlTarget, requestParameters, function(data) {
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
                if (i > 2) {
                    offset += -20;
                } else {
                    offset = -50;
                }
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4) + offset,canvas_image_width,canvas_image_height);
            }

            pdf.save("TESDA-Daily-Accomplishment-Reports.pdf");
        });
    }, 3000);
});
