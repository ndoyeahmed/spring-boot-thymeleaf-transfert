$(document).ready(function () {

    $("#valider").attr("disabled", true);
    $("#confirmPassword").on("keyup", function () {
        const npwd = $("#newPassword").val();
        const cpwd = $("#confirmPassword").val();
        if (npwd.length === cpwd.length) {
            if (npwd !== cpwd) {
                toastError('les mots de passe ne sont pas identiques', 'Erreur');
                $("#valider").attr("disabled", true);
            } else {
                $("#valider").attr("disabled", false);
            }
        } else {
            $("#valider").attr("disabled", true);
        }
    });
    $('#fichier').change(function () {
        if (this.files && this.files[0]) {
            var file = this.files[0];
            var imagefile = file.type;
            var reader = new FileReader();
            reader.onload = imageIsLoaded;
            reader.readAsDataURL(this.files[0]);
        }

    });
});

function verifier() {
    const npwd = $("#newPassword").val();
    const cpwd = $("#confirmPassword").val();
    if (npwd.length === cpwd.length && npwd !== cpwd) {
        $("#valider").prop("disabled", true);
    } else {
        $("#valider").prop("disabled", false);
    }
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

function imageIsLoaded(e) {
    //$("#file").css("color","green");
    //$('#image_preview').css("display", "block");
    $('#image').attr('src', e.target.result);
    $('#image').attr('width', '100px');
    $('#image').attr('height', '100px');
}

function setImageURL(src) {
    $('#image').attr('src', src);
}

function loadImageByName() {
    $.ajax({
        type: "GET",
        contentType: "application/octet-stream",
        url: "/image-update",
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data) {
                // console.log(data);
                $("#thumb1").html('<img alt="test" src="data:image/jpg;base64,' + data + '" />');
            } else {
                console.log('not disabled fields');
            }
        },
        error: function (e) {
            console.log(e);
            // $("#btn-search").prop("disabled", false);
        }
    });
}

function getConnectedUserImg() {
    let photo;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "connected-user",
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (data.response) {
                photo = data.response.photo;
            } else {
                console.log('not disabled fields');
            }
        },
        error: function (e) {
            console.log(e);
            // $("#btn-search").prop("disabled", false);
        },
        complete: function () {
            $("imgdiv").html('<img alt=\"t\" th:src=\"@{\'image-update/' + photo ? photo : 'default.jpg' + '\'\" id=\"image\"/>')
        }
    });
}
