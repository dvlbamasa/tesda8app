window.onload = function () {
    var papGroupType = $('#papGroupType').val();
    var papName = $('#papName').val();
    var measure = $('#measure').val();

    $.getJSON('/api/papData/' + papGroupType + '/papGroup/filter',
        {
            measure: measure,
            papName: papName
        },
        function(data) {
            var data = data;
            for (var i = 0; i < data.length; i++) {
                var successIndicators = data[i].successIndicatorDataList;
                for (var j = 0; j < successIndicators.length; j++) {
                    generateGraph("/api/graph/successIndicator/" + successIndicators[j].id + "/dataPoints",
                        "successIndicatorGraph" + successIndicators[j].id, data[i].name + " - " + successIndicators[j].target + (successIndicators[j].isPercentage ?  '%' : "") + ' ' + successIndicators[j].measures, "Target", "Total");
                }
            }
        }
    );
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

            pdf.save("TESDA-" + papGroupTypeLabel + " Graphs.pdf");
        });
    }, 3000);

});
