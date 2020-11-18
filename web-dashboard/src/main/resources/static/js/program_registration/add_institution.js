
$("#submitButton").click(function(event) {
    if($('#addInstitutionForm').valid()) {
        event.preventDefault();
        var currentElement = $(this);
        swal({
            title: "Add Institution?",
            text: "Kindly check the details before saving.",
            icon: "warning",
            buttons: ["No", "Yes"],
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    swal("Institution successfully added!", {
                        icon: "success",
                    }).then((value) => {
                        $('#addInstitutionForm').submit();
                    });
                } else {
                    swal("Verify the details before saving.");
                }
            });
    }
});
