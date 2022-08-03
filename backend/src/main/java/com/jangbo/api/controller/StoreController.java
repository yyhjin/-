package com.jangbo.api.controller;

import com.jangbo.api.request.StoreEditPatchReq;
import com.jangbo.api.request.StoreRegisterPostReq;
import com.jangbo.api.response.StoreInfoRes;
import com.jangbo.api.service.StoreService;
import com.jangbo.db.entity.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Api(value = "상점api", tags={"상점"})
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService storeService;
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

//    /*상점 목록 조회*/
//    @GetMapping
//    @ApiOperation(value = "상점 목록 조회 api", notes="전체 조회(대기화면에 뿌릴거임)",httpMethod = "GET")
//    public ResponseEntity<List<StoreInfoRes>> findAll() {
//        List<StoreInfoRes> storeInfoResList = storeService.findAll();
//        return new ResponseEntity<List<StoreInfoRes>>(storeInfoResList, HttpStatus.OK);
//    }
 /* 상점 정보 조회*/
    @GetMapping("/{storeNo}")
    @ApiOperation(value = "상점 정보 조회 api", notes="상점번호로 정보조회(방정보 하나하나 )",httpMethod = "GET")
    public ResponseEntity<StoreInfoRes> findById(@PathVariable("storeNo") Integer storeNo) {
        StoreInfoRes storeInfoRes = storeService.findById(storeNo);
        return new ResponseEntity<StoreInfoRes>(storeInfoRes, HttpStatus.OK);
    }

    /* 상점 등록 */
    @PostMapping
    @ApiOperation(value = "상점 등록 api", notes="상점등록",httpMethod = "POST")
    public ResponseEntity<Integer> save(@RequestBody StoreRegisterPostReq storeRegisterPostReq) {
        Integer savedStoreNo = storeService.save(storeRegisterPostReq);
        return new ResponseEntity<Integer>(savedStoreNo, HttpStatus.CREATED);
    }

    /* 상점 수정 */
    @PatchMapping("/{storeNo}")
    @ApiOperation(value = "상점 수정 api", notes="상점 정보 수정, 바뀐 부분만 업데이트 해준다.",httpMethod = "PATCH")
    public ResponseEntity<Integer> update(@PathVariable("storeNo") Integer storeNo,@RequestBody StoreEditPatchReq storeEditPatchReq) {
        Integer updatedStoreNo = storeService.update(storeNo,storeEditPatchReq);
        return new ResponseEntity<Integer>(updatedStoreNo, HttpStatus.CREATED);
    }

    /*상점 삭제*/
    @DeleteMapping("/{storeNo}")
    @ApiOperation(value = "상점 정보 삭제 api", notes="상점번호로 정보삭제",httpMethod = "DELETE")
    public ResponseEntity<Integer> delete(@PathVariable("storeNo") Integer storeNo) {

        storeService.delete(storeNo);

        return new ResponseEntity<Integer>(storeNo, HttpStatus.NO_CONTENT);
    }
}
