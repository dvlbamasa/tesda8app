window.onload = function () {
    var natureOfAppointmentTypeValue = document.getElementById("natureOfAppointmentDetails.natureOfAppointmentType");
    if (natureOfAppointmentTypeValue.value === "REGULAR") {
        document.getElementById('validityDiv').style.display="none";
    } else {
        document.getElementById('validityDiv').style.display="";
    }

    document.getElementById("natureOfAppointmentDetails.natureOfAppointmentType").onchange = function() {
        var natureOfAppointmentTypeValue1 = document.getElementById("natureOfAppointmentDetails.natureOfAppointmentType");
        if (natureOfAppointmentTypeValue1.value === "REGULAR") {
            document.getElementById('validityDiv').style.display="none";
        } else {
            document.getElementById('validityDiv').style.display="";
        }
    }

    var remarkDetailsRemarkTypeValue = document.getElementById("remarkDetails.remarkType");
    if (remarkDetailsRemarkTypeValue.value === "ADDITIONAL") {
        document.getElementById('dateOfApprovalDiv').style.display="none";
    } else {
        document.getElementById('dateOfApprovalDiv').style.display="";
    }

    document.getElementById("remarkDetails.remarkType").onchange = function() {
        var remarkDetailsRemarkTypeValue1 = document.getElementById("remarkDetails.remarkType");
        if (remarkDetailsRemarkTypeValue1.value === "ADDITIONAL") {
            document.getElementById('dateOfApprovalDiv').style.display="none";
        } else {
            document.getElementById('dateOfApprovalDiv').style.display="";
        }
    }

}

$("#submitButton").click(function(event) {
    if ( $('#updateTrainer').valid()) {
        event.preventDefault();
        swal({
            title: "Update Trainer?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Trainer successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#updateTrainer').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
