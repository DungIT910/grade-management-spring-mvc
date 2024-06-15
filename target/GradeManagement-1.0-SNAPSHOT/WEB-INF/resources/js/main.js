
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