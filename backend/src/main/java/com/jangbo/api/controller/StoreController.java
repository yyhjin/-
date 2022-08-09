package com.jangbo.api.controller;

import com.jangbo.api.request.RoomEditPatchReq;
import com.jangbo.api.request.StoreEditPatchReq;
import com.jangbo.api.request.StoreRegisterPostReq;
import com.jangbo.api.response.RoomInfoRes;
import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.api.service.FileService;
import com.jangbo.api.service.StoreService;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Api(value = "상점api", tags={"상점"})
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    FileService fileService;

//    @GetMapping("/{storeNo}")
//    @ApiOperation(value = "상점 정보 조회 api" , notes="상점번호로 상점정보를 불러온다.",httpMethod = "GET")
//    public ResponseEntity<StoreRegisterPostReq> findByNo(@PathVariable int storeNo){
//
//        return new ResponseEntity<StoreRegisterPostReq>(storeService.findByNo(storeNo),HttpStatus.OK);
//    }

//    @DeleteMapping("/{storeNo}")
//    @ApiOperation(value = "상점 정보 삭제 api", notes="상점번호로 정보삭제",httpMethod = "DELETE")
//    public ResponseEntity<String> deleteStore(@PathVariable int storeNo) {
//        storeService.deleteBystoreNo(storeNo);
//        return new ResponseEntity<String>("삭제되었다",HttpStatus.NO_CONTENT);
//    }
//
//    @PostMapping
//    @ApiOperation(value= "상점 등록api", notes="사용자의 입력 정보를 받아 상점을 등록한다.",httpMethod = "POST")
//    public ResponseEntity<String> registStore(StoreRegisterPostReq storeRegisterPostReq){
//        storeService.createStore(storeRegisterPostReq);
//        return new ResponseEntity<String>("성공했다",HttpStatus.OK);
//    }

//    /*상점 전체 목록 조회*/
    @GetMapping
    @ApiOperation(value = "상점 목록 조회 api", notes="전체 조회(대기화면에 뿌릴거임)",httpMethod = "GET")
    public ResponseEntity<List<StoreInfoRes>> findAll() {
        List<StoreInfoRes> storeInfoResList = storeService.findAll();
        return new ResponseEntity<List<StoreInfoRes>>(storeInfoResList, HttpStatus.OK);
    }

        /*상점 목록 조회*/
    @GetMapping("market/{marketNo}")
    @ApiOperation(value = "한 시장안에 상점 목록 api", notes="전체 조회(대기화면에 뿌릴거임)",httpMethod = "GET")
    public ResponseEntity<List<Store>> findStores(@PathVariable("marketNo") Integer marketNo) {
        List<Store> storeList = storeService.findStoresByMarket(marketNo);
        return new ResponseEntity<List<Store>>(storeList, HttpStatus.OK);
    }
 /* 상점 정보 조회*/
    @GetMapping("/{storeNo}")
    @ApiOperation(value = "상점 정보 조회 api", notes="상점번호로 정보조회(방정보 하나하나 )",httpMethod = "GET")
    public ResponseEntity<StoreInfoRes> findStoreById(@PathVariable("storeNo") Integer storeNo) {
        StoreInfoRes storeInfoRes = storeService.findStoreById(storeNo);
        return new ResponseEntity<StoreInfoRes>(storeInfoRes, HttpStatus.OK);
    }

//    /* 상점 등록 */
//    @PostMapping
//    @ApiOperation(value = "상점 등록 api", notes="상점등록",httpMethod = "POST")
//    public ResponseEntity<Integer> save(@RequestPart(value="storeName") String storeName, @RequestPart(value="storeCategory") String storeCategory,
//                                        @RequestPart(value="storePhone") String storePhone, @RequestPart(value="storeAddr") String storeAddr,
//                                        @RequestPart(value="marketNo") int marketNo, @RequestPart(value="sellerNo") int sellerNo,
//                                         @RequestPart(value="file" , required = false) MultipartFile file)throws IOException {
////        @RequestBody StoreRegisterPostReq storeRegisterPostReq,
//        String imgPath = fileService.fileUpload(file);
//        StoreRegisterPostReq store = new StoreRegisterPostReq(storeName, storeCategory, storePhone, storeAddr, marketNo, sellerNo, imgPath);
//        Integer savedStoreNo = storeService.save(store);
//
////        fileDto.setStoreNo(savedStoreNo);
////        fileDto.toEntity();//안되면 여기부터 수정
//        //상점번호를 받아서 이미지 테이블에 상점번호를 같이 저장하자
//        return new ResponseEntity<Integer>(savedStoreNo, HttpStatus.CREATED);
//    }
    /////////////////////////////////////////////////////////////

    @PostMapping
    @ApiOperation(value = "상점 등록 api", notes="상점등록",httpMethod = "POST")
    public ResponseEntity<Integer> save(@RequestPart(value="storeRegisterPostReq")StoreRegisterPostReq store,
                                        @RequestPart(value="file" , required = false) MultipartFile file)throws IOException {
        String name = store.getStoreName();
        String imgPath = fileService.fileUpload(store,file);
        store.setStoreImg(imgPath);
       Integer savedStoreNo = storeService.save(store);
        //상점번호를 받아서 이미지 테이블에 상점번호를 같이 저장하자
        return new ResponseEntity<Integer>(savedStoreNo, HttpStatus.CREATED);
    }






//    @PostMapping
//    @ApiOperation(value = "이미지 등록 api", notes="상점등록",httpMethod = "POST")
//    public ResponseEntity<Integer> save(@RequestParam(required = false) MultipartFile multipartFile)throws IOException {
//        String imgPath = fileUploadService.fileUpload(multipartFile);
//
////        fileDto.setStoreNo(savedStoreNo);
////        fileDto.toEntity();//안되면 여기부터 수정
//        //상점번호를 받아서 이미지 테이블에 상점번호를 같이 저장하자
//        return new ResponseEntity<Integer>(savedStoreNo, HttpStatus.CREATED);
//    }





    /* 상점 수정 */
    @PatchMapping("/{storeNo}")
    @ApiOperation(value = "상점 수정 api", notes="상점 정보 수정, 바뀐 부분만 업데이트 해준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> updateStore(@PathVariable("storeNo") Integer storeNo,@RequestBody StoreEditPatchReq storeEditPatchReq) {
        Integer updatedStoreNo = storeService.updateStore(storeNo,storeEditPatchReq);
        return new ResponseEntity<Integer>(updatedStoreNo, HttpStatus.NO_CONTENT);
    }

    /*상점 삭제*/
    @DeleteMapping("/{storeNo}")
    @ApiOperation(value = "상점 정보 삭제 api", notes="상점번호로 정보삭제",httpMethod = "DELETE")
    public ResponseEntity<Integer> delete(@PathVariable("storeNo") Integer storeNo) {

        storeService.delete(storeNo);

        return new ResponseEntity<Integer>(storeNo, HttpStatus.NO_CONTENT);
    }

    /* 방 정보 조회 */
    @GetMapping("/room/{storeNo}")
    @ApiOperation(value = "방 정보 조회 api", notes="상점번호로 방 정보 조회(방안의 내용)",httpMethod = "GET")
    public ResponseEntity<RoomInfoRes> findRoomById(@PathVariable("storeNo") Integer storeNo) {
        RoomInfoRes roomInfoRes = storeService.findRoomById(storeNo);
        return new ResponseEntity<RoomInfoRes>(roomInfoRes, HttpStatus.OK);
    }
    /* 방 정보 수정 */
    @PatchMapping("room/{storeNo}")
    @ApiOperation(value = "방 정보 수정 api", notes="방 정보 수정, 바뀐 부분만 업데이트 해준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> updateRoom(@PathVariable("storeNo") Integer storeNo,@RequestBody RoomEditPatchReq roomEditPatchReq) {
        Integer updatedStoreNo = storeService.updateRoom(storeNo,roomEditPatchReq);
        return new ResponseEntity<Integer>(updatedStoreNo, HttpStatus.NO_CONTENT);
    }

}
