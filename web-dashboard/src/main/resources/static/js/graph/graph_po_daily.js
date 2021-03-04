window.onload = function () {
    setTimeout(function () {
        document.getElementById('downloadGraphButton').disabled=false;
    }, 3000);

    // LEYTE
    var requestParameters = {
        operatingUnit: 'LEYTE'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "leyteGraph", "EGAC Accomplishment Reports in Leyte", "Target", "Output");

    // BILIRAN
    var requestParameters = {
        operatingUnit: 'BILIRAN'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "biliranGraph", "EGAC Accomplishment Reports in Biliran", "Target", "Output");

    // SOUTHERN_LEYTE
    var requestParameters = {
        operatingUnit: 'SOUTHERN_LEYTE'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "southernLeyteGraph", "EGAC Accomplishment Reports in Southern Leyte", "Target", "Output");

    // SAMAR
    var requestParameters = {
        operatingUnit: 'SAMAR'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "samarGraph", "EGAC Accomplishment Reports in Samar", "Target", "Output");

    // NORTHERN_SAMAR
    var requestParameters = {
        operatingUnit: 'NORTHERN_SAMAR'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "northernSamarGraph", "EGAC Accomplishment Reports in Northern Samar", "Target", "Output");

    // EASTERN_SAMAR
    var requestParameters = {
        operatingUnit: 'EASTERN_SAMAR'
    }
    generateGraph("/api/graph/poReport", requestParameters,
        "easternSamarGraph", "EGAC Accomplishment Reports in Eastern Samar", "Target", "Output");
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
    window.scrollTo(0, 0);
    var HTML_Width = $(".canvas_div_pdf").width();
    var HTML_Height = $(".canvas_div_pdf").height();
    var top_left_margin = 15;
    var PDF_Width = HTML_Width+(top_left_margin*2);
    var PDF_Height = (PDF_Width*1.5)+(top_left_margin*2);
    var canvas_image_width = HTML_Width;
    var canvas_image_height = HTML_Height;

    var totalPDFPages = Math.ceil(HTML_Height/PDF_Height)-1;
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
});
