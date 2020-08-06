<template>
    <div>
        <h3>{{title}}</h3>

        <ul :key="update_key">
            <li v-for="(item, index) in list_items" :key="item"
                @click="setActive(index)">
                <div v-if="active_list[index]" style="width: 100%;" class="quas-list-item-active">
                    <span>{{item}}</span>
                </div>
                <div v-else style="width: 100%;" class="quas-list-item-inactive">
                    <span>{{item}}</span>
                </div>
            </li>
        </ul>
        <div style="text-align: center;">
            <button class="quas-submit-button" @click="push">新建</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "QuasList",
        props: {
            /**
             * 列表标题
             */
            title: {
                type: String,
                default: ""
            },
            /**
             * 列表项内容
             */
            value: {
                type: Array,
                required: true
            }
        },
        data() {
            return {
                list_items: this.value,
                active_list: [],
                update_key: 0
            };
        },
        methods: {
            /**
             * 设置当前列表项为选中
             * @param index 当前项的下标
             * @private
             */
            setActive(index) {
                for (let i = 0; i < this.active_list.length; i++) {
                    this.active_list[i] = (i === index);
                }

                this.update_key++;
                /**
                 * 返回选中项的下标
                 */
                this.$emit("callback", index);
            },
            /**
             * 添加新的列表项
             * @private
             */
            push() {
                this.list_items.push("新建分析");
                this.active_list.push(false);
            }
        },
        beforeMount() {
            // eslint-disable-next-line no-unused-vars
            for (let i in this.value) {
                this.active_list.push(false);
            }
        },
        watch: {
            list_items: {
                handler(new_value) {
                    /**
                     * 更新列表项内容
                     */
                    this.$emit("input", new_value);
                },
                deep: true
            }
        }
    }
</script>

<style scoped>
    h3 {
        text-align: center;
        margin-top: 10px;
    }

    ul, li {
        margin: 0;
        padding: 0;
    }

    ul {
        overflow-y: scroll;
        min-height: 600px;
    }

    ul::-webkit-scrollbar {
        display: none;
    }

    li {
        margin-bottom: 10px;
    }

    span, a {
        width: 100%;
        margin-left: 10px;
    }

    a {
        cursor: pointer;
    }
</style>