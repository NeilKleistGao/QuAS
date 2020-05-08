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
    import QuasSortItem from "@/components/QuasSortItem";
    export default {
        name: "QuasSortList",
        components: {QuasSortItem},
        props: {
            editable: {
                type: Boolean,
                default: false
            },
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
            erase(index) {
                if (this.labels[index] === "") {
                    this.labels.splice(index, 1);
                    this.update_key++;
                }
            },
            push() {
                this.labels.push("new item");
                this.update_key++;
            },
            dragStartWhenEditing(index) {
                this.drag_old_index = index;
            },
            dragEnterWhenEditing(index) {
                this.drag_new_index = index;
            },
            dragEndWhenEditing() {
                let temp = this.labels[this.drag_old_index];
                this.labels.splice(this.drag_old_index, 1);
                this.labels.splice(this.drag_new_index, 0, temp);
                this.update_key++;
            },
            dragStart(index) {
                this.drag_old_index = index;
            },
            dragEnter(index) {
                this.drag_new_index = index;
            },
            exchange(arr, id1, id2) {
                [arr[id1], arr[id2]] = [arr[id2], arr[id1]];
                return arr;
            },
            dragEnd() {
                this.result = this.exchange(this.result, this.drag_old_index, this.drag_new_index);
                console.log(this.result);
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