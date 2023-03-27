/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

var dropZone = document.getElementById('dropZoneCurriculum');
var uploadButton = document.getElementById('uploadButtonCurriculum');

dropZone.addEventListener('dragover', function (e) {
    e.preventDefault();
});

dropZone.addEventListener('drop', function (e) {
    e.preventDefault();

    var file = e.dataTransfer.files[0];

    // Verificar que el archivo es una imagen
    if (!file.type.startsWith('application/pdf')) {
        var message = document.getElementById('messageCurriculum');
        message.innerHTML = "<p id='UploadErrorMessageCurriculum' class='errorMessage'>Error: El arxiu seleccionat no es un pdf.</p>";
        return;
    }

    var reader = new FileReader();

    reader.onload = function (e) {
        var errorMessage = document.getElementById("UploadErrorMessageCurriculum")
        if (errorMessage != null)
            errorMessage.remove();
        
        var message = document.getElementById('messageCurriculum');
        message.innerHTML = "<p class='successMessage'>"+file.name+"</p>";
    }

    reader.readAsDataURL(file);
    document.getElementById('fileInputCurriculum').files = e.dataTransfer.files;
    document.getElementById('fileInputCurriculum').style.display = 'none';
});

uploadButton.addEventListener('click', function (e) {
    document.getElementById('fileInputCurriculum').click();
});

document.getElementById('fileInputCurriculum').addEventListener('change', function (e) {
    var file = this.files[0];

    // Verificar que el archivo es una imagen
    if (!file.type.startsWith('application/pdf')) {
        var message = document.getElementById('messageCurriculum');
        message.innerHTML = "<p id='UploadErrorMessageCurriculum' class='errorMessage'>Error: El archivo seleccionado no es una imagen.</p>";
        return;
    }

    var reader = new FileReader();

    reader.onload = function (e) {
        var errorMessage = document.getElementById("UploadErrorMessageCurriculum")
        if (errorMessage != null)
            errorMessage.remove();
        
        var message = document.getElementById('messageCurriculum');
        message.innerHTML = "<p class='successMessage'>"+file.name+"</p>";
    }

    reader.readAsDataURL(file);
});
