<?php
include "connect.php";

$result = array();

$query = "SELECT * FROM `thanhvien`";
$data = mysqli_query($conn, $query);

if ($data) {
    while ($row = mysqli_fetch_assoc($data)) {
        $result[] = $row;
    }

    if (!empty($result)) {
        $arr = [
            'success' => true,
            'message' => "Lấy danh sách thành công",
            'result' => $result
        ];
    } else {
        $arr = [
            'success' => false,
            'message' => "Không có người dùng nào",
            'result' => $result
        ];
    }
} else {
    $arr = [
        'success' => false,
        'message' => "Lỗi truy vấn",
        'result' => $result
    ];
}

print_r(json_encode($arr));
?>
