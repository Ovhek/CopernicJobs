/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var dropZone = document.getElementById('dropZone');
var uploadButton = document.getElementById('uploadButton');

dropZone.addEventListener('dragover', function (e) {
    e.preventDefault();
});

dropZone.addEventListener('drop', function (e) {
    e.preventDefault();

    var file = e.dataTransfer.files[0];

    // Verificar que el archivo es una imagen
    if (!file.type.startsWith('image/')) {
        var message = document.getElementById('message');
        message.innerHTML = "<p id='UploadErrorMessage' class='errorMessage'>Error: l'arxiu seleccionat no es una imatge.</p>";
        return;
    }

    var reader = new FileReader();

    reader.onload = function (e) {
        var errorMessage = document.getElementById("UploadErrorMessage")
        if (errorMessage != null)
            errorMessage.remove();

        var message = document.getElementById('message');
        var img = document.getElementById("previewImg");
        img.src = reader.result;
    }

    reader.readAsDataURL(file);
    document.getElementById('fileInput').files = e.dataTransfer.files;
    document.getElementById('fileInput').style.display = 'none';
});

uploadButton.addEventListener('click', function (e) {
    document.getElementById('fileInput').click();
});

document.getElementById('fileInput').addEventListener('change', function (e) {
    var file = this.files[0];

    // Verificar que el archivo es una imagen
    if (!file.type.startsWith('image')) {
        var message = document.getElementById('message');
        message.innerHTML = "<p id='UploadErrorMessage' class='errorMessage'>Error: l'arxiu seleccionat no es una imatge.</p>";
        return;
    }

    var reader = new FileReader();

    reader.onload = function (e) {
        var errorMessage = document.getElementById("UploadErrorMessage")
        if (errorMessage != null)
            errorMessage.remove();

        var message = document.getElementById('message');
        var img = document.getElementById("previewImg");
        img.src = reader.result;
    }

    reader.readAsDataURL(file);
});
