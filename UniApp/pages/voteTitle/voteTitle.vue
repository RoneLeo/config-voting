<template>
	<view class="page">
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
				<textarea maxlength="-1" @input="textareaBInput" placeholder="多行文本输入框"></textarea>
			</view>
			<view class="cu-form-group">
				<view class="title">题目类型</view>
				<radio-group @change="radioChange">
					<label v-for="(item, index) in items" :key="item.value" style="display: block;padding-top: 10upx">
						<radio :value="item.value" :checked="index === current" />
						<view style="display: inline-block;">{{item.name}}</view>
					</label>
				</radio-group>
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
					<radio-group v-model="option.optionType" >
						<label v-for="(item, index) in optionsRadios" :key="item.value" style="display: block;padding-top: 10upx">
							<radio :value="item.value" :checked="option.optionType === item.value" />
							<view style="display: inline-block;">{{item.name}}</view>
						</label>
					</radio-group>
				</view>
				<view v-show="option.optionType=='text' || option.optionType=='other'" class="cu-form-group">
					<view class="title">选项名称</view>
					<input placeholder="请填写投票主题" name="input"></input>
				</view>
				<view v-show="option.optionType=='num'" class="cu-form-group">
					<view class="title">选项名称</view>
					<input class="uni-input" type="number" placeholder="只能输入数字" />
				</view>
				<view v-show="option.optionType=='img' && option.imgSrc != ''" class="cu-form-group">
					<view class="title">选项图片</view>
					<image src="http://192.168.0.198:8080/5d5628df-edd8-4b34-b375-e38352cefcac" mode="aspectFit" style="width: 300upx;height: 200upx;"></image>
				</view>
				<view class="cu-form-group">
					<view class="title">选项说明</view>
					<textarea maxlength="-1" v-model="option.xxsm" placeholder="多行文本输入框"></textarea>
				</view>
				<view style="display: flex;justify-content: flex-end;">
					<button v-show="option.optionType=='img' && option.imgSrc == ''" class="cu-btn bg-blue" style="margin-left: 30upx;" @tap="chooseImage">上传</button>
					<!-- <button class="cu-btn bg-blue" @tap="saveOption" style="margin-left: 30upx;">保存</button> -->
				</view>
			</view>

			<view class="padding">
				<view class="justify-center text-blue" @tap="addOption" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;">+</view>
				<view class="justify-center text-blue" @tap="saveAll" style="border: 1px dashed #ccc; border-radius: 10upx;height: 60upx;display: flex;align-items: center;font-size: 18px;margin-top: 20upx;">save</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				items: [{
						value: 'USA',
						name: '单选（投票人选择的选项数等于1）'
					},
					{
						value: 'CHN',
						name: '多选（投票人选择的选项大于等于2）',
						checked: 'true'
					},
					{
						value: 'BRA',
						name: '不定项（投票人选择的选项大于等于1）'
					}
				],
				optionsRadios: [{
						value: 'text',
						name: '文字',
						checked: 'true'
					},
					{
						value: 'img',
						name: '多媒体(图片、动画)',
					},
					{
						value: 'num',
						name: '数字'
					},
					{
						value: 'other',
						name: '其他(自行填写)'
					}
				],
				newOptions: [
					
				],
				newOption: {
					optionType: 'text',
					imgSrc: ''
				},
				current: 0
			};
		},
		methods: {
			saveAll() {
				console.log(this.newOptions);
			},
			chooseImage() {
				uni.chooseImage({
					count: 1, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['album'], //从相册选择
					success: function(res) {
						console.log(res);
						if (res.tempFilePaths.length) {
							
							let arr = res.tempFilePaths[0].split(':');
							arr.shift();
							this.newOption.imgSrc = arr.join(':')
							console.log(this.newOption.imgSrc)
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
					optionType: 'text',
					imgSrc: ''
				}];
				console.log(this.newOptions, item)
				let temp = this.newOptions.concat(item);
				console.log(temp)
				this.newOptions = this.newOptions.concat(item)
				console.log(this.newOptions)
			},
			optionsRadioChange: function(evt) {
				// console.log(evt, evt.target, evt.target.value)
				for (let i = 0; i < this.optionsRadios.length; i++) {
					if (this.optionsRadios[i].value == evt.target.value) {
						this.newOption.optionType = this.optionsRadios[i].value;
						console.log(this.newOption.optionType);
						break;
					}
				}
			},
			radioChange: function(evt) {
				for (let i = 0; i < this.items.length; i++) {
					if (this.items[i].value == evt.target.value) {
						this.current = i;
						break;
					}
				}
			}
		}
	}
</script>

<style lang="scss">
	.cu-form-group uni-textarea {
		margin: 0;
	}
</style>
