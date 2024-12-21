package com.example.lop124lttd03.nhom_t_d_p;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lop124lttd03.nhom_t_d_p.Interface.ItemClickListener;
import com.example.lop124lttd03.nhom_t_d_p.R;
import com.example.lop124lttd03.nhom_t_d_p.Service.ApiService;
import com.example.lop124lttd03.nhom_t_d_p.Service.RetrofitClient;
;
import com.example.lop124lttd03.nhom_t_d_p.Model.Photo;
import com.example.lop124lttd03.nhom_t_d_p.utils.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBookFragment extends Fragment implements ItemClickListener {

    private static final String ARG_SACH = "sach";
    ImageView bookCover;
    TextView bookTitle;
    TextView author;
    TextView viewer;
    TextView demsao1, demsao2;
    int bookId = 0;
    Button btn_docSach;
    public ApiService apiService;
    RecyclerView recyclerViewDanhGia;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewBookFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ViewBookFragment newInstance(Photo photo) {
        ViewBookFragment fragment = new ViewBookFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SACH, photo);
        fragment.setArguments(args);
        return fragment;
    }

//    public static ViewBookFragment newInstance(int idbook) {
//        ViewBookFragment fragment = new ViewBookFragment();
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_SACH, idbook);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String bookId = getArguments().getString("bookId");
            // Sử dụng bookId để lấy và hiển thị thông tin sách
        }
    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_book, container, false);

        //Lấy id người dùng theo phiên đăng nhập
        SharedPreferences preferences = requireActivity().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        int user_id = preferences.getInt("user_id", -1);

        //ÁNH xạ
        bookCover = view.findViewById(R.id.bookCover);
        bookTitle = view.findViewById(R.id.bookTitle);
        author = view .findViewById(R.id.author);
        recyclerViewDanhGia = view.findViewById(R.id.recyclerDanhGia);
        demsao1 = view.findViewById(R.id.demsao1);
        demsao2 = view.findViewById(R.id.ratingText);

        ImageView star1 = view.findViewById(R.id.star1);
        ImageView star2 = view.findViewById(R.id.star2);
        ImageView star3 = view.findViewById(R.id.star3);
        ImageView star4 = view.findViewById(R.id.star4);
        ImageView star5 = view.findViewById(R.id.star5);

        //Lấy thông tin sách từ bundle
        Photo sach = (Photo) getArguments().getSerializable(ARG_SACH);

        Utils utils = new Utils();
        bookTitle.setText(sach.getTitle());
        author.setText("Tác giả: " + sach.getAuthor());
        bookId = sach.getBook_id();
        Glide.with(getContext()).load(utils.BASE_URL + sach.getCover_image()).into(bookCover);

        //Đếm sao
//        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
//        compositeDisposable.add(apiService.getCountRating(bookId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        danhGiaModel -> {
//                            if (danhGiaModel.isSuccess()){
//                                double sao = danhGiaModel.getResult().get(0).getDemsao();
//                                demsao1.setText(sao+"");
//                                demsao2.setText(sao+"/5");
//                                //làm sáng sao
//                                ImageView[] stars = {star1, star2, star3, star4, star5};
//
//                                for (int i = 0; i < stars.length; i++) {
//                                    if (i < Math.floor(sao)) {
//                                        // Làm sáng sao nếu nhỏ hơn số sao nguyên
//                                        stars[i].setImageResource(R.drawable.ic_star_filled);
//                                    } else if (i < sao) {
//                                        // Làm sáng sao một phần nếu nằm giữa số sao nguyên và trung bình
//                                        //stars[i].setImageResource(R.drawable.ic_star_half_filled);
//                                    } else {
//                                        // Sao còn lại không sáng
//                                        stars[i].setImageResource(R.drawable.ic_star_empty);
//                                    }
//                                }
//                            }
//                        }
//                ));

        //Yeeu thichs
//        ImageView btn_heart = view.findViewById(R.id.icon_heart);
//        btn_heart.setImageResource(R.drawable.ic_heart_empty);
//
//        Drawable emptyHeart = ContextCompat.getDrawable(getContext(), R.drawable.ic_heart_empty);
//        Drawable filledHeart = ContextCompat.getDrawable(getContext(), R.drawable.ic_heart_selected);
//
//        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
//        compositeDisposable.add(apiService.checkSachYeuThich(sach.getBook_id(), user_id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        sachYeuThichModel -> {
//
//                            Log.d("API Response", "Response: " + sachYeuThichModel.toString());
//
//                            if(sachYeuThichModel.isSuccess()){
//                                if(sachYeuThichModel.isResult()){
//                                    btn_heart.setImageDrawable(filledHeart);
//                                } else {
//                                    btn_heart.setImageDrawable(emptyHeart);
//                                }
//                            } else {
//                                Toast.makeText(getContext(), "Không thể xác định trạng thái yêu thích", Toast.LENGTH_SHORT).show();
//                            }
//                        }, throwable -> {
//                            Log.e("API", "Lỗi gọi API: " + throwable.getMessage());
//                            Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
//                        }
//                ));
//
//        //Lượt xem
//        viewer = view.findViewById(R.id.viewer);
//        compositeDisposable.add(apiService.getSachById(bookId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        photoModel -> {
//                            if(photoModel.isSuccess()){
//                                viewer.setText(photoModel.getResult().get(0).getView()+"");
//                            }
//                        }
//                ));
//
//        compositeDisposable.add(apiService.getDanhGia(bookId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        danhGiaModel -> {
//                            Log.d("ThongThongBaoBao",danhGiaModel.toString());
//                            if(danhGiaModel.isSuccess()) {
//                                array = danhGiaModel.getResult();
//                                listDanhGia = new DanhGiaAdapter(getActivity(),array);
//                                recyclerViewDanhGia.setAdapter(listDanhGia);
//                                recyclerViewDanhGia.setLayoutManager(new LinearLayoutManager(getContext()));
//                            }
//                        }
//                ));
//
//        //Quay lại
//        ImageButton backButton = view.findViewById(R.id.backButtonViewBook);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                moveToTrangChu();
//            }
//        });




//        btn_heart.setImageDrawable(emptyHeart);
//        btn_heart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Lấy drawable hiện tại của ImageView
//                Drawable currentDrawable = btn_heart.getDrawable();
//                if (currentDrawable != null) {
//                    // Kiểm tra nếu drawable hiện tại là trái tim rỗng
//                    if (currentDrawable.getConstantState().equals(emptyHeart.getConstantState())) {
//                        btn_heart.setImageDrawable(filledHeart);
//                        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
//                        compositeDisposable.add(apiService.ThemSachVaoMucYeuThich(sach.getBook_id(), user_id)
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(
//                                        sachYeuThichModel -> {
//                                            if(sachYeuThichModel.isSuccess()) {
//                                                Toast.makeText(getContext(), sachYeuThichModel.getMessage()+"", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(getContext(), sachYeuThichModel.getMessage()+"", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }, throwable -> {
//                                            Log.e("API", "Lỗi gọi API: " + throwable.getMessage());
//                                            Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
//                                        }
//                                ));
//                    } else {
//                        btn_heart.setImageDrawable(emptyHeart);
//                        //XoaYeuThichSach();
//                        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
//                        compositeDisposable.add(apiService.XoaSachKhoiMucMucYeuThich(sach.getBook_id(), user_id)
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(
//                                        sachYeuThichModel -> {
//                                            if(sachYeuThichModel.isSuccess()) {
//                                                Toast.makeText(getContext(), sachYeuThichModel.getMessage()+"", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(getContext(), sachYeuThichModel.getMessage()+"", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }, throwable -> {
//                                            Log.e("API", "Lỗi gọi API: " + throwable.getMessage());
//                                            Toast.makeText(getContext(), "Có lỗi xảy ra, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
//                                        }
//                                ));
//                    }
//                } else {
//                    Log.d("Thong báo", "Drawable is null");
//                }
//            }
//        });
//
//
//        //come danh gia
//        LinearLayout DanhGiaButton = view.findViewById(R.id.button_danhgia);
//        DanhGiaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //moveToTrangDanhGia();
//                ReviewBookFragment reviewBookFragment = new ReviewBookFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("BOOK_ID", sach.getBook_id());
//                // Truyền category_id vào bundle
//                reviewBookFragment.setArguments(bundle);
//                requireActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.view_pager_trangchu, reviewBookFragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//
//        //Doc sach
//        Button readbutton = view.findViewById(R.id.readButton);
//        readbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Toast.makeText(getContext(), user_id+"", Toast.LENGTH_SHORT).show();
//                apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
//                //thêm vô mục đã đọc
//                compositeDisposable.add(apiService.ThemSachVaoMucDaDoc(sach.getBook_id(),user_id)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                sachDaDocModel -> {
//                                    if(sachDaDocModel.isSuccess()){
//                                        //Toast.makeText(getContext(), sachDaDocModel.getMessage(), Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        //Toast.makeText(getContext(), sachDaDocModel.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }, throwable -> {
//                                    //Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                        ));
//                //lấy id sách để truền qua giao diện đoọc
//                compositeDisposable.add(apiService.updateViewer(sach.getBook_id())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(
//                                photoModel -> {
//                                    if(photoModel.isSuccess()) {
//                                    }
//                                }, throwable -> {
//
//                                }
//                        ));
//                Intent intent = new Intent(getActivity(),ReadingBookActivity.class);
//                intent.putExtra("book_id", bookId);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void moveToTrangChu(){
        getActivity().getSupportFragmentManager().popBackStack();
    }

//    private void moveToTrangDanhGia() {
//        SachTheoChuDeFragment sachTheoChuDeFragment = new SachTheoChuDeFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("CATEGORY_ID", category_id);
//        // Truyền category_id vào bundle
//        sachTheoChuDeFragment.setArguments(bundle);
//        ReviewBookFragment reviewBookFragment = new ReviewBookFragment();
//        requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, reviewBookFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    public void OnItemClick(Photo photo) {
        //
    }

    @Override
    public void onclick(View view, int pos, boolean isLongClick) {

    }
}