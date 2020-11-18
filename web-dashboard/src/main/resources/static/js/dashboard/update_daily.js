$("#submitButton").click(function(event) {
    if($('#poForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#poForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});

$("#submitButton2").click(function(event) {
    if($('#certificationRateForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#certificationRateForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton3").click(function(event) {
    if($('#roGSForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#roGSForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton4").click(function(event) {
    if($('#roT2Form').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#roT2Form').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton5").click(function(event) {
    if( $('#institutionBasedForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#institutionBasedForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});



$("#submitButton6").click(function(event) {
    if($('#enterpriseBasedGSForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#enterpriseBasedGSForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});


$("#submitButton7").click(function(event) {
    if($('#enterpriseBasedT2Form').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#enterpriseBasedT2Form').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});



$("#submitButton8").click(function(event) {
    if($('#communityBasedForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#communityBasedForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});



$("#submitButton9").click(function(event) {
    if($('#ttiReportEGForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#ttiReportEGForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});



$("#submitButton10").click(function(event) {
    if($('#ttiReportACForm').valid()) {
        event.preventDefault();
        swal({
            title: "Update Report?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Report successfully updated!", {
                        icon: "success",
                    }).then((value) => {
                        $('#ttiReportACForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});