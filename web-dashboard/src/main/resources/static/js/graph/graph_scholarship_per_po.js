window.onload = function () {

    var yearValue = $('#yearValue').val();
    var scholarshipTypeValue = $('#scholarshipTypeValue').val();
    var monthValue = $('#month').val();

    const egacList = [
        {
            name: 'enrolled',
            egacType: 'ENROLLED',
            titleExtension: ' (Utilization Rate)',
            legend1: 'Slots',
            legend2: 'Enrolled',
            legend3: 'Utilization Rate'
        },
        {
            name: 'graduated',
            egacType: 'GRADUATED',
            titleExtension: ' (Completion Rate)',
            legend1: 'Enrolled',
            legend2: 'Graduated',
            legend3: 'Completion Rate'
        },
        {
            name: 'assessed',
            egacType: 'ASSESSED',
            titleExtension: ' (Assessment Rate)',
            legend1: 'Graduated',
            legend2: 'Assessed',
            legend3: 'Assessment Rate'
        },
        {
            name: 'certified',
            egacType: 'CERTIFIED',
            titleExtension: ' (Certification Rate)',
            legend1: 'Assessed',
            legend2: 'Certified',
            legend3: 'Certification Rate'
        }
    ];

    for (var index = 0; index < egacList.length; index++) {
        var requestParameters = {
            month: monthValue,
            year: yearValue,
            scholarshipType: scholarshipTypeValue,
            egacType: egacList[index].egacType
        }
        generateGraph("/api/graph/scholarship/graphData/perPO", requestParameters,
            egacList[index].name + "Graph",
            scholarshipTypeValue + egacList[index].titleExtension,
            egacList[index].legend1,
            egacList[index].legend2,
            egacList[index].legend3);
    }

}


function generateGraph(urlTarget, requestParameters, chartName, title, legend1, legend2, legend3) {
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
                name: legend3,
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
                var offset = 50 + i * 15;
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4) - offset,canvas_image_width,canvas_image_height);
            }

            pdf.save("TESDA-Scholarship-Reports.pdf");
        });
    }, 3000);
});
