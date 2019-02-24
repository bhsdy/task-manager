<template>
  <div class="yui-panel">
    <div class="panel-title" v-if="!nameHide">{{name}}</div>
    <div class="panel-body">
      <slot></slot>
    </div>
  </div>
</template>

<script>

export default {
  name: 'panel',
  props: {
    name: String,
    nameHide: {type: Boolean, default: false},
  }
}
</script>

<style lang="scss" scoped>
  @import '../../../assets/css/config.scss';

  .yui-panel{
    background-color: $bg-color-container;
    overflow: hidden;
    border: none;
    position: relative;
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
    .panel-title{
      font-size: 14px;
      height: 32px;
      line-height: 32px;
      padding: 0 20px;
      border-bottom: 1px solid #ebf1f3;
    }
    .panel-body{
      padding: $base-spacing;
    }
  }
</style>
