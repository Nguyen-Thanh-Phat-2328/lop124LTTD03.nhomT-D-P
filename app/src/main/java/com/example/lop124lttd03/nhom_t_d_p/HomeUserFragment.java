package com.example.lop124lttd03.nhom_t_d_p;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lop124lttd03.nhom_t_d_p.Interface.ItemClickListener;
import com.example.lop124lttd03.nhom_t_d_p.R;
import com.example.lop124lttd03.nhom_t_d_p.Service.ApiService;
import com.example.lop124lttd03.nhom_t_d_p.Service.RetrofitClient;
import com.example.lop124lttd03.nhom_t_d_p.adapters.PhotoAdapter;
import com.example.lop124lttd03.nhom_t_d_p.adapters.itemBookAdapter;
import com.example.lop124lttd03.nhom_t_d_p.Model.Photo;
import com.example.lop124lttd03.nhom_t_d_p.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeUserFragment extends Fragment implements ItemClickListener {

    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Photo> mListPhoTo;
    private RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;
    private itemBookAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiService apiService;


    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if(currentPosition == mListPhoTo.size() - 1) {
                mViewPager2.setCurrentItem(0);
            }
            else {
                mViewPager2.setCurrentItem(currentPosition + 1);
            }
        }
    };

    // TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeUserFragment newInstance(String param1, String param2) {
        HomeUserFragment fragment = new HomeUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void getsachmoi() {
        compositeDisposable.add(apiService.getsachmoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> { // Xử lý kết quả từ API
                            if (photoModel.isSuccess()) { // Kiểm tra trạng thái trả về
                                List<Photo> mListPhoto = photoModel.getResult(); // Lấy danh sách từ kết quả
                                if (mListPhoto != null && !mListPhoto.isEmpty()) {
                                    // Khởi tạo adapter
                                    itemBookAdapter adapter = new itemBookAdapter(getActivity(), mListPhoto,this);
                                    recyclerView1.setAdapter(adapter);
                                } else {
                                    Toast.makeText(getActivity(), "Danh sách sách trống", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), photoModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            // Xử lý lỗi khi API thất bại
                            Toast.makeText(getActivity(), "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    private void getsachdecu() {
        compositeDisposable.add(apiService.getsachdecu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> { // Xử lý kết quả từ API
                            if (photoModel.isSuccess()) { // Kiểm tra trạng thái trả về
                                List<Photo> mListPhoto = photoModel.getResult(); // Lấy danh sách từ kết quả
                                if (mListPhoto != null && !mListPhoto.isEmpty()) {
                                    // Khởi tạo adapter
                                    itemBookAdapter adapter = new itemBookAdapter(getActivity(), mListPhoto,this);
                                    recyclerView2.setAdapter(adapter);
                                } else {
                                    Toast.makeText(getActivity(), "Danh sách sách trống", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), photoModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            // Xử lý lỗi khi API thất bại
                            Toast.makeText(getActivity(), "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    private void getsachtop10doanhnhan() {
        compositeDisposable.add(apiService.getach1op10doanhnhan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> { // Xử lý kết quả từ API
                            if (photoModel.isSuccess()) { // Kiểm tra trạng thái trả về
                                List<Photo> mListPhoto = photoModel.getResult(); // Lấy danh sách từ kết quả
                                if (mListPhoto != null && !mListPhoto.isEmpty()) {
                                    // Khởi tạo adapter
                                    itemBookAdapter adapter = new itemBookAdapter(getActivity(), mListPhoto,this);
                                    recyclerView3.setAdapter(adapter);
                                } else {
                                    Toast.makeText(getActivity(), "Danh sách sách trống", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), photoModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
// Xử lý lỗi khi API thất bại
                            Toast.makeText(getActivity(), "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }
    private void getsachsuckhoe() {
        compositeDisposable.add(apiService.getsachsuckhoe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        photoModel -> { // Xử lý kết quả từ API
                            if (photoModel.isSuccess()) { // Kiểm tra trạng thái trả về
                                List<Photo> mListPhoto = photoModel.getResult(); // Lấy danh sách từ kết quả
                                if (mListPhoto != null && !mListPhoto.isEmpty()) {
                                    // Khởi tạo adapter
                                    itemBookAdapter adapter = new itemBookAdapter(getActivity(), mListPhoto,this);
                                    recyclerView4.setAdapter(adapter);
                                } else {
                                    Toast.makeText(getActivity(), "Danh sách sách trống", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), photoModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            // Xử lý lỗi khi API thất bại
                            Toast.makeText(getActivity(), "Lỗi kết nối: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }


    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);

        mViewPager2 = view.findViewById(R.id.view_pager_sachPr);
        mCircleIndicator3 = view.findViewById(R.id.indicator3);

        //setting viewpager2
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        mViewPager2.setPageTransformer(compositePageTransformer);

        mListPhoTo = getListPhoto();
        PhotoAdapter photoAdapter = new PhotoAdapter(mListPhoTo);
        mViewPager2.setAdapter(photoAdapter);
        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }
        });

        recyclerView1 = view.findViewById(R.id.recyclerViewNewBooks);
        recyclerView2 = view.findViewById(R.id.recyclerViewNewBooks2);
        recyclerView3 = view.findViewById(R.id.recyclerViewNewBooks3);
        recyclerView4 = view.findViewById(R.id.recyclerViewNewBooks4);

        apiService = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiService.class);
        getListPhotoItem();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        getsachmoi();

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        getsachdecu();

        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        getsachtop10doanhnhan();

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(linearLayoutManager4);
        getsachsuckhoe();
        //khoi tao list

//        mListPhoTo = new ArrayList<>();
//        getListPhotoItem();
//
//        //khoi tao adapter
//        adapter  = new itemBookAdapter(getActivity().getApplicationContext(), mListPhoTo );
//        //them deer test
//        itemBookAdapter adapter2 = new itemBookAdapter(getActivity().getApplicationContext(),mListPhoTo);
//        recyclerView2.setAdapter(adapter);
//
//        itemBookAdapter adapter3 = new itemBookAdapter(getActivity().getApplicationContext(),mListPhoTo);
//        recyclerView3.setAdapter(adapter);
//
//        itemBookAdapter adapter4 = new itemBookAdapter(getActivity().getApplicationContext(),mListPhoTo);
//        recyclerView4.setAdapter(adapter);


        // Thê Loại sách
//        ImageButton menuButton = view.findViewById(R.id.left_image);
//        menuButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTheLoaiFragment();
//            }
//        });

        //Search Sách
//        ImageButton searchButton = view.findViewById(R.id.right_image);
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showSearchFragment();
//            }
//        });

        //Click sách
        //itemBookAdapter adapter3 = new itemBookAdapter(getActivity().getApplicationContext(), mListPhoTo, this::onItemClick);
//recyclerView1.setAdapter(adapter3);

        // Nếu đã có dữ liệu trong savedInstanceState, phục hồi vị trí cuộn
//        if (savedInstanceState != null) {
//            int position = savedInstanceState.getInt("scroll_position", 0);
//            linearLayoutManager.scrollToPosition(position);
//        }

        return view;
    }

    // Lưu trạng thái khi Fragment 1 bị hủy (chỉ cần lưu lại vị trí cuộn của RecyclerView)
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        int position = line.findFirstVisibleItemPosition();
//        outState.putInt("scroll_position", position);
//    }

    private void getListPhotoItem() {

    }

    //Click sách
//    public void onItemClick(int bookId) {
//        if (getActivity() instanceof TrangChuUser) {
//            ((TrangChuUser) getActivity()).showViewBookFragment(bookId);
//        }
//    }

    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();

        list.add(new Photo(R.drawable.img_1,"",""));
        list.add(new Photo(R.drawable.img_2,"",""));
        list.add(new Photo(R.drawable.img_3,"",""));
        list.add(new Photo(R.drawable.img_4,"",""));
        list.add(new Photo(R.drawable.img_5,"",""));

        list.add(new Photo(R.drawable.img_1,"",""));
        list.add(new Photo(R.drawable.img_2,"",""));
        list.add(new Photo(R.drawable.img_3,"",""));
        list.add(new Photo(R.drawable.img_4,"",""));
        list.add(new Photo(R.drawable.img_5,"",""));

        return list;
    }



    //The loại sách
//    private void showTheLoaiFragment(){
//        TheLoaiSachUserFragment theLoaiSachUserFragment = new TheLoaiSachUserFragment();
//        requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, theLoaiSachUserFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    //Search Sách
//    private void showSearchFragment(){
//        SearchFragment searchFragment = new SearchFragment();
//    requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, searchFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    //viewBook
//    private void showViewBookFragment(int bookId){
//        ViewBookFragment viewBookFragment = ViewBookFragment.newInstance(bookId);
//        requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.view_pager_trangchu, viewBookFragment)
//                .addToBackStack(null)
//                .commit();
//    }

    //luu hanh dong truoc khi thoat

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }
    @Override
    public void OnItemClick(Photo photo) {
//        ViewBookFragment viewBookFragment = ViewBookFragment.newInstance(photo);
//        FragmentTransaction transaction  = requireActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.view_pager_trangchu, viewBookFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    @Override
    public void onclick(View view, int pos, boolean isLongClick) {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}