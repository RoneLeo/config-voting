<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">科研组专家打分活动</block>
		</cu-custom>
		<view class="margin-xl bg-white light shadow-blur" style="border-radius: 20upx;text-align: center;">
			<view style="font-size: 24px;line-height: 2;">科研组专家打分活动</view>
			<view style="font-size: 18px;line-height: 2;">2019-02-10 ~ 2019-03-10</view>
			<view @longtap="saveToAlbum" class="padding" style="text-align: center;">
				<QRCode ref="qrcode" :val="val" :onval="true" @result="qrCoderRes" />
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isBack: false,
				ID: null,
				val: '',
				qrcodeResUrl: ''
			};
		},
		onLoad(params) {
			this.ID = params.id; //获取到轰动id来更新数据
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		},
		mounted() {
			this.val = 'http://192.168.0.198:8080/#/pages/mark/mark?id=1';
		},
		methods: {
			saveToAlbum() {
				uni.showActionSheet({
					itemList: ['保存到相册', '取消'],
					success: (res) => {
						if (!res.tapIndex) {
							if (this.qrcodeResUrl != "") {
								//从服务器上 下载图片到本地
							}
						} else {
							uni.showToast({
								title: '取消保存到相册',
								icon: 'none'
							})
						}
					},
					fail: function(res) {
						console.log(res.errMsg);
					}
				});
			
			},
			qrCoderRes(res) {
				this.qrcodeResUrl = res;
				console.log(res);
			},
		}
	}
</script>

<style lang="scss">

</style>
