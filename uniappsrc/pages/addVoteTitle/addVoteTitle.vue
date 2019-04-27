<template>
	<view class="page">
		<login></login>
		<cu-custom bgColor="bg-blue" :isBack="true">
			<block slot="content">新增/编辑投票题目</block>
		</cu-custom>
		<view class="">
			<view class="cu-bar bg-white">
				<view class="action">
					<text class="cuIcon-titles text-blue"></text>
					<text class="text-xl text-bold">题目信息</text>
				</view>
			</view>
			<view class="cu-form-group">
				<view class="title">题目名称</view>
				<textarea v-model="formData.bt" maxlength="-1" placeholder="请输入题目名称"></textarea>
			</view>
			<view class="cu-form-group">
				<view class="title">题目类型</view>
				<radio-group @change="radioChange">
					<label v-for="(item, radioIndex) in items" :key="item.value" style="display: block;padding-top: 10upx">
						<radio :value="item.value" :checked="item.value === formData.tplx" style="margin-right: 10upx;" />
						<view style="display: inline-block;">{{item.name}}</view>
					</label>
				</radio-group>
			</view>
			<view v-show="numShow" class="cu-form-group">
				<view class="title">选项数</view>
				<view class="num-flex">
					<view class="input-wrapper">
						<input v-model="formData.zxs" type="number">
					</view>

					<text class="num-text">-</text>
					<view class="input-wrapper">
						<input v-model="formData.zds" type="number">
					</view>
				</view>
			</view>
		</view>

		<view style="margin-top: 50upx;">
			<view class="cu-bar bg-white">
				<view class="action">
					<text class="cuIcon-titles text-blue"></text>
					<text class="text-xl text-bold">选项信息</text>
				</view>
			</view>
			<view class="cu-card shadow" v-for="(option, index) in newOptions" :key="index" style="border: 1px solid #ededed;border-radius: 20upx;margin: 10upx 20upx;padding: 10upx 10upx 20upx;">
				<view class="cu-form-group">
					<view class="title">选项格式</view>
					<radio-group :ref="'radio' + index" @change="optionsRadioChange(index)" v-model="option.xxlx">
						<label v-for="(item, radioIndex) in optionsRadios" :key="item.value" style="display: block;padding-top: 10upx">
							<radio :value="item.value" :checked="option.xxlx === item.value" style="margin-right: 10upx;" />
							<view style="display: inline-block;">{{item.name}}</view>
						</label>
					</radio-group>
				</view>
				<view v-show="option.xxlx=='1' || option.xxlx=='3'" class="cu-form-group">
					<view class="title">选项名称</view>
					<input v-model="option.xxmc" placeholder="请填写选项名称" name="input"></input>
				</view>
				<!-- <view v-show="option.xxlx=='3'" class="cu-form-group">
					<view class="title">选项名称</view>
					<input class="uni-input" v-model="option.xxmc" type="number" placeholder="只能输入数字" />
				</view> -->
				<view v-show="option.xxlx=='2' && option.xxmc != ''" class="cu-form-group">
					<view class="title">选项图片</view>
					<image :src="option.xxmc" mode="aspectFit" style="width: 300upx;height: 200upx;"></image>
				</view>
				<view class="cu-form-group">
					<view class="title">选项描述</view>
					<textarea maxlength="-1" v-model="option.xxnr" placeholder="可不填"></textarea>
				</view>
				<view style="display: flex;justify-content: flex-end;">
					<button v-show="option.xxlx=='2' && option.xxmc == ''" class="cu-btn bg-blue" style="margin-left: 30upx;" @tap="chooseImage(index)">
						<image src="../../static/img/upload2.png" mode="" style="width: 50upx;height: 50upx;"></image>
					</button>
				</view>
			</view>

			<view class="padding">
				<view class="justify-center text-blue" @tap="addOption" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
				<view class="justify-center text-blue" @tap="saveAll" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;margin-top: 20upx;">save</view>
			</view>
			<!-- <Modal :modalShow="modalShow" modalTitle="警告（Warning）" modalContent="请完成所有设置选项" @hide="modalHide"></Modal> -->
			<view class="cu-modal" :class="modalShow2?'show':''">
				<view class="cu-dialog">
					<view class="cu-bar bg-white justify-end">
						<view class="content">消息</view>
						<view class="action" @tap="modalHide2">
							<text class="cuIcon-close text-red"></text>
						</view>
					</view>
					<view class="padding-xl">
						是否需要设置其他选项？
						<switch @change="switchChange" />
						<view style="color: red;line-height: 2;" v-show="errorShow">
							请完成所有设置选项。
						</view>
					</view>
					<view class="cu-bar bg-white justify-end">
						<view class="action">
							<button class="cu-btn line-blue text-blue" @tap="modalHide2">取消</button>
							<button class="cu-btn bg-blue margin-left" @tap="saveData">确定</button>

						</view>
					</view>
				</view>
			</view>
			<!-- <Modal :modalShow="modalShow2" modalTitle="消息" modalContent="是否需要设置其他选项？" @hide="modalHide2">
				<slot>111</slot>
			</Modal> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				items: [{
						value: '1',
						name: '单选（只能选择一个）'
					},
					{
						value: '2',
						name: '多选（设置选择多个）',
					}
				],
				optionsRadios: [{
						value: '1',
						name: '文字',
						checked: 'true'
					},
					{
						value: '2',
						name: '多媒体(图片、动画)',
					},
					{
						value: '3',
						name: '数字'
					}
				],
				newOptions: [],
				newOption: {
					xxlx: '1',
					xxmc: '',
					xxnr: ''
				},
				formData: {
					tplx: '1',
					bt: '',
					hdid: null,
					zxs: '1',
					zds: '1',
					qt: 0
				},
				numShow: false,
				errorShow: false,
				modalShow2: false
			};
		},
		created() {
			console.log(this.$store.state.voteData.id);
		},
		methods: {
			switchChange: function(e) {
				this.formData.qt = e.target.value ? 1 : 0;
				
				console.log('switch2 发生 change 事件，携带值为', e.target.value)
			},
			modalHide2() {
				this.errorShow = false;
				this.modalShow2 = false;
			},
			saveData() {
				this.formData.hdid = this.$store.state.voteData.id || 11;
				console.log(this.formData, this.newOptions);
				for (let key in this.formData) {
					if (this.formData[key] === '' || this.formData[key] === null) {
						this.errorShow = true;
					}
				}
				for(let i = this.newOptions.length; i >= 0; i --) {
					for(let key in this.newOptions[i]) {
						if(key != 'xxnr' && (this.newOptions[i][key] === '' || this.newOptions[i][key] === null)) {
							this.errorShow = true;
						}
					}
				}
				if(!this.errorShow) {
					this.formData.list = JSON.stringify(this.newOptions);
					this.$api.post('/voting/addOneVoting', this.formData).then(res => {
						console.log(res)
						this.modalHide2();
						if(res.resCode == 200) {
							uni.showToast({
								title: '保存成功！',
								icon: 'none'
							})
							uni.navigateBack()
						}else {
							uni.showToast({
								title: res.resMsg,
								icon: 'none'
							})
						}
					})
				}
			},
			saveAll() {
				this.modalShow2 = true;
			},
			chooseImage(index) {
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: (res) => {
						console.log(res);
						let tempFilePaths = res.tempFilePaths;
						if (tempFilePaths.length) {
							uni.uploadFile({
								url: this.$url + '/files/uploadOne', //仅为示例，非真实的接口地址
								filePath: tempFilePaths[0],
								name: 'file',
								success: (uploadFileRes) => {
									console.log(uploadFileRes.data);
									let data = JSON.parse(uploadFileRes.data)
									this.newOptions[index].xxmc = this.$url + '/files/' + data.data;
								}
							});
						}

					},
					fail() {
						uni.showToast({
							title: '未选择到图片',
							icon: 'none'
						})
					}
				});
			},

			addOption() {
				let item = [{
					xxlx: '1',
					xxmc: '',
					xxnr: ''
				}];
				let temp = this.newOptions.concat(item);
				this.newOptions = this.newOptions.concat(item)
			},
			optionsRadioChange: function(index) {
				let ref = 'radio' + index
				let radios = this.$refs[ref][0].radioList.length && this.$refs[ref][0].radioList;
				radios.forEach(item => {
					if (item.radioChecked) {
						this.newOptions[index].xxlx = item.radioValue;
						this.newOptions[index].xxmc = '';
						this.newOptions[index].xxnr = '';
					}
				})
			},
			radioChange: function(evt) {
				this.formData.tplx = evt.target.value;
				if (evt.target.value == 2) { //题目类型是多选的话
					this.numShow = true;
				} else {
					this.numShow = false;
					this.formData.zxs = '1';
					this.formData.zds = '1';
				}
			}
		}
	}
</script>

<style lang="scss">
	.cu-form-group uni-textarea {
		margin: 0;
	}

	.cu-form-group .title {
		width: 150upx;
	}

	.num-flex {
		display: flex;
		justify-content: flex-start;
		color: #666;

		.input-wrapper {
			border: 1px solid #e3e3e3;
			border-radius: 10upx;

			input {
				width: 180upx;
				text-align: center;
			}
		}

		.num-text {
			width: 50upx;
			display: flex;
			justify-content: center;
			align-items: center;
		}

	}
</style>
