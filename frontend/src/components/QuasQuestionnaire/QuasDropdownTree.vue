<template>
    <div :key="update_key">
        <ul v-for="(item, index) in content" :key="index">
            <li :class="getClassByLevel(content[index].level)">
                <input v-model="content[index].label" class="quas-design-text" type="text"
                        @blur.capture="erase(index)"
                        @keyup.ctrl.enter="insert(index)"
                        @keyup.ctrl.left="decreaseLevel(index)"
                        @keyup.ctrl.right="increaseLevel(index)">
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        name: "QuasDropdownTree",
        props: {
            value: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                content: this.value,
                update_key: 0
            };
        },
        watch: {
            content: {
                handler(new_value){
                    this.$emit("input", new_value);
                },
                deep: true
            }
        },
        methods: {
            getClassByLevel(level) {
                return "quas-tree-node-level-" + level.toString();
            },
            erase(index) {
                if (this.content[index].label === "") {
                    this.content.splice(index, 1);
                    this.update_key++;
                }
            },
            insert(index) {
                this.content.splice(index + 1, 0, {
                    label: "new item",
                    level: this.content[index].level
                });
            },
            increaseLevel(index) {
                if (this.content[index].level < 5 && index != 0 && this.content[index].level <= this.content[index - 1].level) {
                    this.content[index].level++;
                }
                this.update_key++;
            },
            decreaseLevel(index) {
                if (this.content[index].level > 0) {
                    this.content[index].level--;
                }
                this.update_key++;
            }
        }
    }
</script>

<style scoped>

</style>