<template>
	<view class="cu-modal" :class="modalShow?'show':''">
		<view class="cu-dialog">
			<view class="cu-bar bg-white justify-end">
				<view class="content">{{modalTitle}}</view>
				<view class="action" @tap="hideModal">
					<text class="cuIcon-close text-red"></text>
				</view>
			</view>
			<view class="padding-xl">
				{{modalContent}}
				<view>
					<slot></slot>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "modal",
		props: ['modalShow', 'modalTitle', 'modalContent'],
		data() {
			return {
			}
		},
		methods: {
			hideModal() {
				this.$emit('hide')
				// this.modalShow = false;
			}
			
		}
	}
</script>

<style>
</style>
