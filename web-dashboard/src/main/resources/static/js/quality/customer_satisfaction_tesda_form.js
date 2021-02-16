$(document).ready(function(){
    document.getElementById("tesdaForm.employmentOthers").onkeyup = function() {checkRadioButtonOthers()};
    document.getElementById("tesdaForm.employmentAdmin").onkeyup = function() {checkRadioButtonAdmin()};
});

window.onload = function() {
    var $recaptcha = document.querySelector('#g-recaptcha-response');

    if($recaptcha) {
        $recaptcha.setAttribute("required", "required");
    }
};

function checkRadioButtonOthers() {
    $("#OTHERS_EMPLOYMENT").prop("checked", true);
}

function checkRadioButtonAdmin() {
    $("#ADMIN").prop("checked", true);
}

function othersOnclick() {
    if (document.getElementById("OTHERS_EMPLOYMENT").checked) {
    } else {
        document.getElementById("tesdaForm.employmentOthers").value = "";
    }
}

function adminOnclick() {
    if (document.getElementById("ADMIN").checked) {
    } else {
        document.getElementById("tesdaForm.employmentAdmin").value = "";
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