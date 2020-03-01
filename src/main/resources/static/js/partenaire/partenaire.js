let osById;
let logiciel = [];

$(document).ready(function () {

    /*loadOs();
    loadLogiciel();*/

    personneList();
    /*
        $("select#osDropdown").change(function () {
            const selectedOs = $(this).children("option:selected").val();
            $.ajax({
                url: "/transfert/ordinateur/os/" + selectedOs,
                type: 'GET',
                success: function (x) {
                    osById = x;
                }
            });
        });

        $("#ordinateur-add").submit(function (event) {
            //stop submit the form, we will post it manually.
            event.preventDefault();
            save();
        });*/

    $("#personne-add").submit(function (event) {
        savePersonne();
        event.preventDefault();
    })
});

function addPartner() {
    const idPartner = $("#id-partner").val();

    let partner = {
        id: idPartner ? idPartner : null,
        nom: $("#nom").val(),
        mail: $("#email").val(),
        ninea: $("#ninea").val(),
        tel: $("#tel").val(),
        adresse: $("#adresse").val(),
        /* utilisateurList: [
             {
                 nomComplet: $("prenom_nom").val(),
                 email: $("mail_user").val()
             }
         ]*/
    };

    $.ajax({
        type: !idPartner ? "POST" : "PUT",
        contentType: "application/json",
        url: "/transfert/partenaire",
        data: JSON.stringify(partner),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            setTextToAttrib(null, true);
            if (data.success) {
                toastSuccess('Opération effectuée avec succès', 'Success');
            } else {
                toastError('Echec de l\'opération', 'Erreur');
            }
            listPartner();
        },
        error: function (e) {
            console.log(e);
            toastError('Echec de l\'opération', 'Erreur');
            // $("#btn-search").prop("disabled", false);

        }
    });
}

function listPartner() {
    $.ajax({
        type: "GET",
        url: "/transfert/partenaire/list",
        success: function (data) {

            let json =
                "<table class=\"table table-hover\">" +
                "                            <thead>" +
                "                            <tr>\n" +
                "                                <th>Ninea</th>" +
                "                                <th>Nom</th>" +
                "                                <th>Téléphone</th>" +
                "                                <th>Email</th>" +
                "                                <th>Adresse</th>" +
                "                            </tr>\n" +
                "                            </thead>\n" +
                "<tbody>";
            for (const p of data.data) {
                json = json +
                    "<tr>" +
                    "<td id=\"" + p.id + "\">" + p.ninea + "</td>" +
                    "<td>" + p.nom + "</td>" +
                    "<td>" + p.tel + "</td>" +
                    "<td>" + p.mail + "</td>" +
                    "<td>" + p.adresse + "</td>" +
                    "<td>" +
                    "<button class='btn btn-primary' data-toggle='modal'" +
                    " onclick=\"loadPartner(document.getElementById('" + p.id + "').id)\" id='update-partner'>Modifier</button>" +
                    "</td>" +
                    "</tr>"
            }

            json = json + "</tbody>" +
                "                        </table>";
            $('#feedback').html(json);

        },
        error: function (e) {
            console.log(e);
        }
    });
}

function loadPartner(partner) {
    console.log(partner);
    if (partner) {
        $.get("/transfert/partenaire/" + partner, function (data) {
            console.log(data);
            setTextToAttrib(data, false);
            $('#partenaireModal').modal('show');
        })
    } else console.log('partner is not define')
}

function setTextToAttrib(val, clear) {
    if (clear) {
        $("#id-partner").val(undefined);
        $("#nom").val(undefined);
        $("#email").val(undefined);
        $("#ninea").val(undefined);
        $("#tel").val(undefined);
        $("#adresse").val(undefined);
    } else {
        $("#id-partner").val(val.id);
        $("#nom").val(val.nom);
        $("#email").val(val.mail);
        $("#ninea").val(val.ninea);
        $("#tel").val(val.tel);
        $("#adresse").val(val.adresse);
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

// -----------ordinateur ---------------
function loadLogiciel() {
    $.ajax({
        url: "/transfert/ordinateur/logiciels",
        type: 'GET',
        success: function (x) {
            for (const z of x) {
                $('#logicielList').append('<input type="checkbox" name="logiciel" class="checkbox" value="' + z.id + '" />' + z.libelle + ' ' + z.version);
            }
        }
    });
}

function loadOs() {
    $.ajax({
        url: "/transfert/ordinateur/os",
        type: 'GET',
        success: function (x) {
            for (const z of x) {
                $('#osDropdown').append('<option value="' + z.id + '">' + z.libelle + '</option>');
            }
        }
    });
}

function save() {
    let ordinateur = {
        code: $("#code").val(),
        processeur: $("#processeur").val(),
        ram: $("#ram").val(),
        disque: $("#disque").val(),
        ecran: $("#ecran").val(),
        os: osById,
    };

    $.each($("input[name='logiciel']:checked"), function () {
        logiciel.push($(this).val());
    });

    console.log(logiciel);
    saveOrdinateur(ordinateur);
}

function saveOrdinateur(ordinateur) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/transfert/ordinateur",
        data: JSON.stringify(ordinateur),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log(e);
            toastError('Echec de l\'opération', 'Erreur');
            // $("#btn-search").prop("disabled", false);

        }
    });
}

function savePersonne() {
    const personne = {
        nom: $("#nompersonne"),
        prenom: $("#prenompersonne")
    };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/transfert/personnes/add",
        data: JSON.stringify(personne),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log(e);
            toastError('Echec de l\'opération', 'Erreur');
            // $("#btn-search").prop("disabled", false);

        }
    });
}

function personneList() {
    $.ajax({
        url: "/transfert/personnes/all",
        type: 'GET',
        success: function (x) {
            console.log(x);
        }
    });
}


