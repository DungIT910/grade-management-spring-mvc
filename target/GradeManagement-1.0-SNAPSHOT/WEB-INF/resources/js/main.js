
function delPro(path, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "DELETE"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!" + path);
        });
    }
}


function removeStudent(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "DELETE"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Something wrong!!!" + path);
        });
    }
}

function addStudent(path) {
    var studentId = document.getElementById('studentIdToAdd').value;
    var fullPath = path + encodeURIComponent(studentId);
    fetch(fullPath, {
        method: "POST"
    }).then(res => {
        if (res.status === 200)
            location.reload();
        else
            alert("Sinh viên không tồn tại");
    });
}