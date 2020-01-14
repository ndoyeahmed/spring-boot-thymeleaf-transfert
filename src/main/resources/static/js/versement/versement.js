$(document).ready(function () {
    $("#ninea").keyup(function () {
        searchPartner($("#ninea").val())
    });

    $("#versement-add").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        if ($("#montant").val() >= 75000) {
            console.log('add versement');
        } else {
            toastError('Le montant doit', 'Attention');
        }
    });
});

function addVersement() {
    console.log("versement add")
}

function toastSuccess(message, title) {
    $.toast({
        text: message,
        title: title,
        showHideTransition: 'slide',  // It can be plain, fade or slide
        bgColor: 'green',              // Background color for toast
        textColor: '#fff',            // text color
        allowToastClose: false,       // Show the close button or not
        hideAfter: 5000,              // `false` to make it sticky or time in milliseconds to hide after
        stack: 5,                     // `false` to show one stack at a time count showing the number of toasts that can be shown at once
        textAlign: 'left',            // Alignment of text i.e. left, right, center
        position: 'top-right'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
    })
}

function toastError(message, title) {
    $.toast({
        text: message,
        title: title,
        showHideTransition: 'slide',  // It can be plain, fade or slide
        bgColor: 'red',              // Background color for toast
        textColor: '#fff',            // text color
        allowToastClose: false,       // Show the close button or not
        hideAfter: 5000,              // `false` to make it sticky or time in milliseconds to hide after
        stack: 5,                     // `false` to show one stack at a time count showing the number of toasts that can be shown at once
        textAlign: 'left',            // Alignment of text i.e. left, right, center
        position: 'top-right'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
    })
}

function toastWarning(message, title) {
    $.toast({
        text: message,
        title: title,
        showHideTransition: 'slide',  // It can be plain, fade or slide
        bgColor: '#FFAA04',              // Background color for toast
        textColor: '#fff',            // text color
        allowToastClose: false,       // Show the close button or not
        hideAfter: 5000,              // `false` to make it sticky or time in milliseconds to hide after
        stack: 5,                     // `false` to show one stack at a time count showing the number of toasts that can be shown at once
        textAlign: 'left',            // Alignment of text i.e. left, right, center
        position: 'top-right'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
    })
}

function disabledFields(isDisable) {
    $("#nom").prop( "disabled", isDisable );
    $("#email").prop( "disabled", isDisable );
    $("#adresse").prop( "disabled", isDisable );
    $("#tel").prop( "disabled", isDisable );
}

function clearFields() {
    $("#nom").val("");
    $("#email").val("");
    $("#adresse").val("");
    $("#tel").val("");
}

function searchPartner(event) {
    console.log(event);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/transfert/partenaire/search/" + event,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data) {
                disabledFields(true);
                $("#nom").val(data.nom);
                $("#email").val(data.email);
                $("#adresse").val(data.adresse);
                $("#tel").val(data.tel);
            } else {
                disabledFields(false);
                clearFields();
                console.log('not disabled fields');
            }
        },
        error: function (e) {
            console.log(e);
            disabledFields(false);
            clearFields();
            // $("#btn-search").prop("disabled", false);

        }
    });
}
