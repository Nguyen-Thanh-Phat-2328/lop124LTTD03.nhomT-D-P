<?php
include "connect.php";

// Sửa query để lấy top 10 sách thuộc chủ đề Doanh nhân
$query = "SELECT * FROM books WHERE category_id = 3 ORDER BY created_at DESC LIMIT 10";
$data = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($data)) {
    $result[] = ($row);
}

if (!empty($result)) {
    $arr = [
        'success' => true,
        'message' => "thanh cong",
        'result' => $result
    ];
} else {
    $arr = [
        'success' => false,
        'message' => "khong thanh cong",
        'result' => $result
    ];
}

print_r(json_encode($arr));

?>
