var module = app;

module.directive('ngConfirmClick', [
    function(){
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick || "Are you sure?";
                var clickAction = attr.confirmedClick;
                element.bind('click',function (event) {
                    if ( window.confirm(msg) ) {
                        scope.$eval(clickAction)
                    }
                });
            }
        };
    }]);

module.controller('articleController', ['$scope', 'ngDialog', '$http','Notification',function ($scope, ngDialog, $http,Notification) {
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 0,
        itemsPerPage: 10,
        pagesLength: 15,
        perPageOptions: [10],
        rememberPerPage: 'perPageItems',
        onChange: function () {
            $scope.showArticleList(this.currentPage,this.totalItems);

        }
    };
    $scope.filterNormal = true;
    $scope.filterRetry = false;
    $scope.filterDLQ = false;
    $scope.allArticleList = [];
    $scope.articleShowList = [];

    $http({
        method: "GET",
        url: "article/list.query"
    }).success(function (resp) {
        if(resp.status ==0){
            //$scope.allArticleList = resp.data.articleList.sort();
            $scope.allArticleList = resp.data;
            console.log( $scope.allArticleList);
            console.log( JSON.stringify(resp));
            $scope.showArticleList(1,$scope.allArticleList.length);

        }else {
            Notification.error({message: resp.errMsg, delay: 5000});
        }

    });

    $scope.showArticleList = function (currentPage, totalItem) {
        if($scope.filterStr != ""){
            $scope.filterList(currentPage);
            return;
        }
        var perPage = $scope.paginationConf.itemsPerPage;
        var from = (currentPage - 1) * perPage;
        var to = (from + perPage) > totalItem ? totalItem : from + perPage;
        $scope.stgArticleShowList = $scope.allArticleList.slice(from, to);
        console.log($scope.articleShowList)
        $scope.filterList(currentPage);
        $scope.doSort();
    };

    $scope.sortByKey = function (key) {
        $scope.paginationConf.currentPage = 1;
        $scope.sortOrder = -$scope.sortOrder;
        $scope.sortKey = key;
        $scope.doSort();
    };
    $scope.doSort = function () {

        if($scope.sortKey == 'id'){
            $scope.allArticleList.sort(function (a, b) {return (a.id > b.id) ? $scope.sortOrder : ((b.id > a.id) ? -$scope.sortOrder : 0);  });
        }
        if($scope.sortKey == 'pubDate'){
            $scope.allArticleList.sort(function (a, b) {return (a.pubDate > b.pubDate) ? $scope.sortOrder : ((b.pubDate > a.pubDate) ? -$scope.sortOrder : 0);  });
        }
        $scope.filterList($scope.paginationConf.currentPage)
    };

    $scope.filterStr="";
    $scope.$watch('filterStr', function() {
        $scope.filterList(1);
    });
    /*$scope.$watch('filterNormal', function() {
        $scope.filterList(1);
    });
    $scope.$watch('filterRetry', function() {
        $scope.filterList(1);
    });
    $scope.$watch('filterDLQ', function() {
        $scope.filterList(1);
    });*/
    $scope.filterList = function (currentPage) {
        var lowExceptStr = $scope.filterStr.toLowerCase();
        var canShowList = [];
        $scope.allArticleList.forEach(function (element) {
            console.log(element);
            canShowList.push(element)
        });
        $scope.paginationConf.totalItems = canShowList.length;
        var perPage = $scope.paginationConf.itemsPerPage;
        var from = (currentPage - 1) * perPage;
        var to = (from + perPage) > canShowList.length ? canShowList.length : form+perPage;
        $scope.articleShowList = canShowList.slice(from, to);
    };

    $scope.refreshArticleData = function () {
        $http({
            method: "GET",
            url: "article/list.query"
        }).success(function (resp) {
            if(resp.status ==0){
                $scope.allArticleList = resp.data;
                console.log($scope.allArticleList);
                console.log(JSON.stringify(resp));
                //$scope.showTopicList($scope.paginationConf.currentPage,$scope.allTopicList.length);
                $scope.showArticleList(1,$scope.allArticleList.length);
            }else {
                Notification.error({message: resp.errMsg, delay: 2000});
            }
        });
    };


   /* $scope.filterByType = function(str){
        if($scope.filterRetry){
            if(str.startsWith("%R")){
                return true
            }
        }
        if($scope.filterDLQ){
            if(str.startsWith("%D")){
                return true
            }
        }
        if($scope.filterNormal){
            if(str.startsWith("%") == false){
                return true
            }
        }
        return false;
    };*/

    $scope.showArticleList = function (currentPage,totalItem) {
        if($scope.filterStr != ""){
            $scope.filterList(currentPage);
            return;
        }
        var perPage = $scope.paginationConf.itemsPerPage;
        var from = (currentPage - 1) * perPage;
        var to = (from + perPage)>totalItem?totalItem:from + perPage;
        console.log($scope.allArticleList);
        console.log(from)
        console.log(to)
        $scope.articleShowList = $scope.allArticleList.slice(from, to);
        $scope.paginationConf.totalItems = totalItem ;
        console.log($scope.articleShowList)
        console.log($scope.paginationConf.totalItems)
        $scope.filterList(currentPage);
    };
    $scope.deleteArticle = function (articleId) {
        $http({
            method: "POST",
            url: "article/deleteArticle.do",
            params:{
                id:articleId
            }
        }).success(function (resp) {
            if(resp.status ==0){
                Notification.info({message: "delete success!", delay: 2000});
            }else {
                Notification.error({message: resp.errMsg, delay: 2000});
            }
        });
    };

    $scope.openDeleteArticleDialog = function (article) {
        ngDialog.open({
            template: 'deleteArticleDialog',
            controller: 'deleteArticleDialogController',
            data:{
                article:article,
                consumerData:"asd"
            }
        });
    };





    $scope.openUpdateDialog = function (articleId) {
        $http({
            method: "GET",
            url: "article/update.do",
            params:{
                id:articleId
            }
        }).success(function (resp) {
            if(resp.status ==0){
                $scope.openCreateOrUpdateDialog(resp.data);
            }else {
                Notification.error({message: "失败", delay: 2000});
            }
        });
    };

    $scope.openCreateOrUpdateDialog = function (request) {
        var bIsUpdate = true;
        var bIsCreate = false;

        if(request == null){
            request = [{
                title:"",
                author:"",
                type:"",
                content:"",
                press:"",
                pubDate:""
            }]
            bIsUpdate = false;
            bIsCreate = true;
        }
        $http({
            method: "GET",
            url: "article/list.query"
        }).success(function (resp) {
            if(resp.status ==0){
                console.log(resp);
                console.log(resp.data.inOrder)
                ngDialog.open({
                    template: 'articleModifyDialog',
                    controller: 'articleModifyDialogController',
                    data:{
                        articleRequestList:request,
                        bIsUpdate:bIsUpdate,
                        bIsCreate:bIsCreate
                    }
                });
            }
        });
    }

    $scope.openAddArticleDialog = function () {
        $scope.openCreateOrUpdateDialog(null);
    }

}]);

module.controller('articleModifyDialogController', ['$scope', 'ngDialog', '$http','Notification',function ($scope, ngDialog, $http,Notification) {
        $scope.isEmpty = function(obj){
            if(typeof (obj) == "undefined" || obj == null || obj == undefined || obj == "undefined" || (""+obj).toUpperCase() == "NULL" || ""+obj == ""){ // 0 == "" ->true
                return true ;
            }else if(Object.prototype.toString.call(obj) == "[object Object]"){
                var i ;
                for(i in obj){
                    return false
                }
                return true;
            }
            return false ;
        };
        $scope.postCreateArticleRequest = function (articleRequestItem) {
            console.log(articleRequestItem);
            var request = JSON.parse(JSON.stringify(articleRequestItem));
            console.log("submit data = ",request);

            $http({
                method: "POST",
                url: "article/create.do",
                data:request

            }).success(function (resp) {
                if(resp.status ==0){
                    Notification.info({message: "add success!", delay: 2000});
                }else {
                        Notification.error({message: resp.errMsg, delay: 2000});
                }
            });
        };
        $scope.postUpdateArticleRequest = function (articleRequestItem) {
            console.log(articleRequestItem);
            var request = JSON.parse(JSON.stringify(articleRequestItem));
            console.log("submit data = ",request);
            $http({
                method: "POST",
                url: "article/update.do",
                data:request
            }).success(function (resp) {
                if(resp.status ==0){
                    Notification.info({message: "update success!", delay: 2000});
                }else {
                    Notification.error({message: resp.errMsg, delay: 2000});
                }
            });
        };
    }]
);
