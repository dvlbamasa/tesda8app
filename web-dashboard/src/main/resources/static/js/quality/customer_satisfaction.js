window.onload = function () {
    onRadioChange();
}

    var queries = [8];

const VERY_SATISFACTORY = "VERY_SATISFACTORY";
const SATISFACTORY = "SATISFACTORY";
const POOR = "POOR";
const EMPTY = "EMPTY";

function onRadioChange() {

    let i = 0;
    let total = 0;
    for (i = 0; i < 8; i++) {
        let queryObject = {
            query: "query" + i,
            response: ''
        }
        if (document.getElementById("query" + i + VERY_SATISFACTORY).checked) {
            queryObject.response = 1;
            queries[i] = queryObject;
        } else if (document.getElementById("query" + i + SATISFACTORY).checked) {
            queryObject.response = 3;
            queries[i] = queryObject;
        } else if (document.getElementById("query" + i + POOR).checked) {
            queryObject.response = 5;
            queries[i] = queryObject;
        } else {
            queryObject.response = 6;
            queries[i] = queryObject;
        }
    }
    for (i = 0; i < 8; i++) {
        total += queries[i].response;
    }
    total = total/8;


    var element = document.getElementById("totalRating");
    var labelElement = document.getElementById("totalLabel");
    var totalRatingInput = document.getElementById("totalRatingInput");

    if (total != 6) {
        $("#totalRating").removeClass("total-rating-blur");
        element.classList.add("total-rating");

        if (total < 2) {
            $("#totalRating").removeClass("poor");
            $("#totalRating").removeClass("satisfactory");
            element.classList.add("very-satisfactory");
            labelElement.innerHTML = "Very Satisfactory";
            totalRatingInput.value = VERY_SATISFACTORY;
        } else if (total <= 3) {
            $("#totalRating").removeClass("poor");
            $("#totalRating").removeClass("very-satisfactory");
            element.classList.add("satisfactory");
            labelElement.innerHTML = "Satisfactory";
            totalRatingInput.value = SATISFACTORY;
        } else {
            $("#totalRating").removeClass("satisfactory");
            $("#totalRating").removeClass("very-satisfactory");
            labelElement.innerHTML = "Poor";
            element.classList.add("poor");
            totalRatingInput.value = POOR;
        }
    }
}

/*
$("#submitButton").click(function(event) {
    if ($('#customerSatisfactionForm').valid()) {
        event.preventDefault();
        swal({
            title: "Submit Customer Satisfaction Form?",
            text: "Pakitingnan kung tama ang lahat ng tugon na inyong sinumite.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Maraming Salamat sa Pagtugon!", {
                        icon: "success",
                    }).then((value) => {
                        $('#customerSatisfactionForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
 */