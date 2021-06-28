package com.example.webflux.service.pbi.impl;

import com.example.webflux.common.enums.ResultEnum;
import com.example.webflux.vo.UpdateBatchVO;
import com.example.webflux.domain.UserBean;
import com.example.webflux.service.user.IUserService;
import com.example.webflux.vo.RequestVO;
import com.example.webflux.vo.ResponseVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/8/3 - 23:58
 * @author: Mr_Bangb
 */
@Service
public class NameManagerService extends ManagerService {
    @Resource
    private IUserService userServiceImpl;

    public ResponseVO<ResultEnum> updateNameBatch(UpdateBatchVO requestVO) throws ExecutionException, InterruptedException {
        System.out.println("NameManagerService.updateObjectBatch");
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<List<UserBean>> taskFuture = pool.submit(new QueryAction(Arrays.asList(1,2,3,4,5), userServiceImpl));
        System.out.println(taskFuture.get());
        return null;
    }

    public ResponseVO<List<ResultEnum>> checkInputParams(RequestVO<UpdateBatchVO> requestVO) {
        System.out.println("NameManagerService.checkInputParams");
        return null;
    }

    static class QueryAction extends RecursiveTask<List<UserBean>> {
        private List<Integer> list;
        private IUserService userServiceImpl;

        public QueryAction(List<Integer> list, IUserService userServiceImpl) {
            this.list = list;
            this.userServiceImpl = userServiceImpl;
        }

        @Override
        protected List<UserBean> compute() {
            List<UserBean> users = new ArrayList<>(list.size());
            if (list.size() < 5) {
                List<UserBean> userBeans = (List<UserBean>) userServiceImpl.listByIds(list);
                users.addAll(userBeans);
            } else {
                QueryAction task1 = new QueryAction(list.subList(0,list.size()/2), userServiceImpl);
                task1.fork();
                QueryAction task2 = new QueryAction(list.subList((list.size()/2 +1),list.size()), userServiceImpl);
                task2.fork();
                users.addAll(task1.join());
                users.addAll(task2.join());
            }
            return users;
        }
    }
}