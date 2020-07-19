<template>
    <div>
        <div v-if="!editable" :key="update_key">
            <transition-group name="flip-list" tag="div">
                <div v-for="(label, index) in result"
                     :key="label"
                     :draggable="true"
                     @dragstart="dragStart(index)"
                     @dragenter="dragEnter(index)"
                     @dragend="dragEnd"
                     class="quas-transition-list-item">
                    <quas-sort-item class="quas-sort-item"
                                    :label="label"/>
                </div>

            </transition-group>
        </div>
        <div v-else :key="update_key">
            <quas-sort-item class="quas-sort-item"
                            v-for="(label, index) in labels"
                            :key="index"
                            v-model="labels[index]"
                            :editable="true"
                            :draggable="true"
                            @blur.native.capture="erase(index)"
                            @dragstart.native="dragStartWhenEditing(index)"
                            @dragenter.native="dragEnterWhenEditing(index)"
                            @dragend.native="dragEndWhenEditing"/>
            <button @click="push" class="quas-operation-button">添加</button>
        </div>
    </div>
</template>

<script>
    import QuasSortItem from "@/components/QuasQuestionnaire/QuasSortItem";
    export default {
        name: "QuasSortList",
        components: {QuasSortItem},
        props: {
            /**
             * 是否可编辑
             */
            editable: {
                type: Boolean,
                default: false
            },
            /**
             * 所有带排序项
             */
            value: {
                type: Object,
                required: true
            }
        },
        data() {
            return {
                result: this.value.result,
                labels: this.value.labels,
                update_key: 0,
                drag_new_index: 0,
                drag_old_index: 0
            };
        },
        methods: {
            /**
             * 可编辑状态下删除一个选项
             * @param index 选项下标
             * @private
             */
            erase(index) {
                if (this.labels[index] === "") {
                    this.labels.splice(index, 1);
                    this.update_key++;
                }
            },
            /**
             * 可编辑状态下添加一个选项
             * @private
             */
            push() {
                this.labels.push("new item");
                this.update_key++;
            },
            /**
             * 非可编辑状态下开始拖动
             * @param index 拖动的选项的下标
             * @private
             */
            dragStartWhenEditing(index) {
                this.drag_old_index = index;
            },
            /**
             * 非可编辑状态下拖动更新
             * @param index 拖动选项的当前下标
             * @private
             */
            dragEnterWhenEditing(index) {
                this.drag_new_index = index;
            },
            /**
             * 非可编辑状态下结束拖动，更新列表
             * @private
             */
            dragEndWhenEditing() {
                let temp = this.labels[this.drag_old_index];
                this.labels.splice(this.drag_old_index, 1);
                this.labels.splice(this.drag_new_index, 0, temp);
                this.update_key++;
            },
            /**
             * 可编辑状态下开始拖动
             * @param index 拖动的选项的下标
             * @private
             */
            dragStart(index) {
                this.drag_old_index = index;
            },
            /**
             * 可编辑状态下拖动更新
             * @param index 拖动选项的当前下标
             * @private
             */
            dragEnter(index) {
                this.drag_new_index = index;
            },
            /**
             * 交换数组内两个元素
             * @private
             */
            exchange(arr, id1, id2) {
                [arr[id1], arr[id2]] = [arr[id2], arr[id1]];
                return arr;
            },
            /**
             * 结束拖动，更新列表
             * @private
             */
            dragEnd() {
                this.result = this.exchange(this.result, this.drag_old_index, this.drag_new_index);
                this.update_key++;
            }
        },
        watch: {
            result(new_value) {
                this.$emit("input", {
                    result: new_value
                });
            },
            labels(new_value) {
                this.$emit("input", {
                    labels: new_value
                })
            }
        }
    }
</script>

<style scoped>

</style>