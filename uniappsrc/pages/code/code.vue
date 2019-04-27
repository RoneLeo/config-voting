<template>
	<view class="page">
		<login></login>
		<cu-custom v-show="isBack" bgColor="bg-blue" :isBack="isBack">
			<block slot="content" style="width: calc(100% - 100px);">{{title}}</block>
		</cu-custom>
		<view class="margin-xl bg-white light shadow-blur" style="border-radius: 20upx;text-align: center;">
			<view style="font-size: 24px;line-height: 2;">{{title}}</view>
			<view style="font-size: 18px;line-height: 2;">{{kssj}} ~ {{jssj}}</view>
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
				lx: '',
				id: null,
				title: '',
				kssj: '',
				jssj: '',
				val: '',
				qrcodeResUrl: ''
			};
		},
		onLoad(params) {
			this.lx = params.lx;
			this.id = params.id; //获取到轰动id来更新数据
			this.title = params.title;
			this.kssj = params.kssj;
			this.jssj = params.jssj;
			if (getCurrentPages().length > 1) {
				this.isBack = true;
			}
		},
		mounted() {
			this.val = 'http://192.168.0.198:8080/#/pages' + this.lx == 'mark' ? '/mark/mark?hdid=' + this.id :
				'/vote/vote?hdid=' + this.id;
			console.log(this.val)
		},
		methods: {
			downloadFile(fileName, content) {
				let aLink = document.createElement('a');
				let blob = this.base64ToBlob(content); //new Blob([content]);

				let evt = document.createEvent("HTMLEvents");
				evt.initEvent("click", true, true); //initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
				aLink.download = fileName;
				aLink.href = URL.createObjectURL(blob);
				aLink.dispatchEvent(new MouseEvent('click', {
					bubbles: true,
					cancelable: true,
					view: window
				})); //兼容火狐
			},
			base64ToBlob(code) {
				let parts = code.split(';base64,');
				let contentType = parts[0].split(':')[1];
				let raw = window.atob(parts[1]);
				let rawLength = raw.length;

				let uInt8Array = new Uint8Array(rawLength);

				for (let i = 0; i < rawLength; ++i) {
					uInt8Array[i] = raw.charCodeAt(i);
				}
				return new Blob([uInt8Array], {
					type: contentType
				});
			},
			saveToAlbum() {
				uni.showActionSheet({
					itemList: ['保存到相册', '取消'],
					success: (res) => {
						if (!res.tapIndex) {
							if (this.qrcodeResUrl != "") {
								//从服务器上 下载图片到本地
								this.downloadFile('111', this.qrcodeResUrl)
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
