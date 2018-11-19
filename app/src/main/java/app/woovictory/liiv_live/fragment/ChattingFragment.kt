package app.woovictory.liiv_live.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import app.woovictory.liiv_live.R
import com.sendbird.android.*
import kotlinx.android.synthetic.main.fragment_chatting.view.*

/**
 * Created by VictoryWoo
 */
class ChattingFragment : Fragment() {

    val mChannelUrl = "sendbird_open_channel_44684_b4d3d856ab50975d3b9fa27db1a66ede12615750"
    val CHANNEL_HANDLER_ID = " CHANNEL_HANDLER_CHAT"
    lateinit var mChatAdapter: ChatAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSendButton: Button
    lateinit var mMessageEditText: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_chatting, container, false)

        mSendButton = view.button_chat_send
        mMessageEditText = view.edittext_chat
        mRecyclerView = view.reycler_chat
        mLayoutManager = LinearLayoutManager(context!!)
        mLayoutManager.reverseLayout = true
        mRecyclerView.layoutManager = mLayoutManager


        OpenChannel.getChannel(mChannelUrl, object : OpenChannel.OpenChannelGetHandler {
            override fun onResult(openChannel: OpenChannel?, e: SendBirdException?) {
                if (e != null) {
                    e.printStackTrace()
                    return
                }

                openChannel!!.enter(object : OpenChannel.OpenChannelEnterHandler {
                    override fun onResult(e: SendBirdException?) {
                        if (e != null) {
                            e.printStackTrace()
                            return
                        }

                        mChatAdapter = ChatAdapter(openChannel)
                        mRecyclerView.adapter = mChatAdapter
                    }
                })
            }
        })
        mSendButton.setOnClickListener {
            mChatAdapter.sendMessage(mMessageEditText.text.toString())
            mMessageEditText.setText("")
        }

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    // 스크롤 내리는 이벤트
//                    convertPartlyRVitemScrollDown()
//                } else {
//                    // 스크롤 올리는 이벤트
//                    convertPartlyRVitemScrollUp()
//                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (mLayoutManager.findLastVisibleItemPosition() == mChatAdapter.itemCount - 1) {
                    mChatAdapter.loadPreviousMessages()
//                    convertPartlyRVitemScrollUp()

                }

            }

        })

        return view
    }

    override fun onResume() {
        super.onResume()
        // Receives messages from SendBird servers
        SendBird.addChannelHandler(CHANNEL_HANDLER_ID, object : SendBird.ChannelHandler() {
            override fun onMessageReceived(baseChannel: BaseChannel, baseMessage: BaseMessage) {
                if (baseChannel.url == mChannelUrl && baseMessage is UserMessage) {
                    mChatAdapter.appendMessage(baseMessage)
                    mRecyclerView.scrollToPosition(0)
                }
            }
        })
    }


    override fun onPause() {
        SendBird.removeChannelHandler(CHANNEL_HANDLER_ID)
        super.onPause()

    }

//    fun convertPartlyRVitemScrollUp(){
//        // 스크롤 올리는 이벤트에 적용
//        if(mLayoutManager != null) {
//            var viewsIds = mLayoutManager.findLastCompletelyVisibleItemPosition()
//            if (viewsIds != 0 && viewsIds != 1 && viewsIds != 2 && viewsIds != 3 && viewsIds != 4 && viewsIds != 5 && viewsIds != 6) {
//
//                // 투명으로 변경해야할 항목
//                var viewsIds2 = mLayoutManager.findViewByPosition(viewsIds + 1)
//                var viewsIds3 = mLayoutManager.findViewByPosition(viewsIds)
//                var viewsIds4 = mLayoutManager.findViewByPosition(viewsIds - 1)
//
//                // 투명값이 사라져야할 항목
//                var reverseViewsIds1 = mLayoutManager.findViewByPosition(viewsIds - 3)
//                var reverseViewsIds2 = mLayoutManager.findViewByPosition(viewsIds - 4)
//                var reverseViewsIds3 = mLayoutManager.findViewByPosition(viewsIds - 5)
//                var reverseViewsIds4 = mLayoutManager.findViewByPosition(viewsIds - 6)
//                var reverseViewsIds5 = mLayoutManager.findViewByPosition(viewsIds - 7)
//                var reverseViewsIds6 = mLayoutManager.findViewByPosition(viewsIds - 8)
//                var reverseViewsIds7 = mLayoutManager.findViewByPosition(viewsIds - 9)
//                var reverseViewsIds8 = mLayoutManager.findViewByPosition(viewsIds - 10)
//                var reverseViewsIds9 = mLayoutManager.findViewByPosition(viewsIds - 11)
//                var reverseViewsIds10 = mLayoutManager.findViewByPosition(viewsIds - 12)
//
//                if (viewsIds2 != null) {
//                    var titleByviewsIds: TextView = viewsIds2!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds2!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#68ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#68ffffff")
//                    }
//                }
//                if (viewsIds3 != null) {
//                    var titleByviewsIds: TextView = viewsIds3!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds3!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#84ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#84ffffff")
//                    }
//                }
//                if (viewsIds4 != null) {
//                    var titleByviewsIds: TextView = viewsIds4!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds4!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#95ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#95ffffff")
//                    }
//                }
//
//                // 투명값이 사라져야하는 항목
//                if (reverseViewsIds1 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds1!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds1!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds2 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds2!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds2!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds3 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds3!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds3!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds4 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds4!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds4!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds5 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds5!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds5!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds6 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds6!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds6!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds7 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds7!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds7!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds8 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds8!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds8!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds9 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds9!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds9!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds10 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds10!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds10!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//            }
//        }
//    }
//
//    fun convertPartlyRVitemScrollDown(){
//        // ScrollDown
//        if(mLayoutManager != null) {
//
//            // RecyclerView에 표시되는 Item중 최상단 Item
//            var viewsIds = mLayoutManager.findLastCompletelyVisibleItemPosition()
//
//            if (viewsIds != 0 && viewsIds != 1 && viewsIds != 2 && viewsIds != 3 && viewsIds != 4 && viewsIds != 5 && viewsIds != 6){
//
//                // 투명으로 바뀌어야하는 값
//                var viewsIds2 = mLayoutManager.findViewByPosition(viewsIds + 1)
//                var viewsIds3 = mLayoutManager.findViewByPosition(viewsIds)
//                var viewsIds4 = mLayoutManager.findViewByPosition(viewsIds - 1)
//
//                // 투명에서 돌아와야하는 값
//                var reverseViewsIds1 = mLayoutManager.findViewByPosition(viewsIds - 3)
//                var reverseViewsIds2 = mLayoutManager.findViewByPosition(viewsIds - 4)
//                var reverseViewsIds3 = mLayoutManager.findViewByPosition(viewsIds - 5)
//                var reverseViewsIds4 = mLayoutManager.findViewByPosition(viewsIds - 6)
//                var reverseViewsIds5 = mLayoutManager.findViewByPosition(viewsIds - 7)
//                var reverseViewsIds6 = mLayoutManager.findViewByPosition(viewsIds - 8)
//                var reverseViewsIds7 = mLayoutManager.findViewByPosition(viewsIds - 9)
//                var reverseViewsIds8 = mLayoutManager.findViewByPosition(viewsIds - 10)
//                var reverseViewsIds9 = mLayoutManager.findViewByPosition(viewsIds - 11)
//                var reverseViewsIds10 = mLayoutManager.findViewByPosition(viewsIds - 12)
//
//                // 투명해지는 처리
//                if (viewsIds2 != null) {
//                    var titleByviewsIds: TextView = viewsIds2!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds2!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#68ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#68ffffff")
//                    }
//                }
//                if (viewsIds3 != null) {
//                    var titleByviewsIds: TextView = viewsIds3!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds3!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#84ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#84ffffff")
//                    }
//                }
//                if (viewsIds4 != null) {
//                    var titleByviewsIds: TextView = viewsIds4!!.findViewById(R.id.text_message_name)
//                    var bodyByviewsIds: TextView = viewsIds4!!.findViewById(R.id.text_message_body)
//                    if (titleByviewsIds != null && bodyByviewsIds != null) {
//                        titleByviewsIds.textColor = Color.parseColor("#95ffffff")
//                        bodyByviewsIds.textColor = Color.parseColor("#95ffffff")
//                    }
//                }
//
//                // 투명값 사라지는 작업
//                if (reverseViewsIds1 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds1!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds1!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds2 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds2!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds2!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds3 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds3!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds3!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds4 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds4!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds4!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds5 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds5!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds5!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds6 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds6!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds6!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds7 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds7!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds7!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds8 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds8!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds8!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//                if (reverseViewsIds9 != null) {
//                    var reverseTitleByviewsIds: TextView = reverseViewsIds9!!.findViewById(R.id.text_message_name)
//                    var reverseBodyByviewsIds: TextView = reverseViewsIds9!!.findViewById(R.id.text_message_body)
//                    if (reverseTitleByviewsIds != null && reverseBodyByviewsIds != null) {
//                        reverseTitleByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                        reverseBodyByviewsIds.textColor = Color.parseColor("#FFFFFF")
//                    }
//                }
//            }
//
//        }
//    }

    inner class ChatAdapter(channel: OpenChannel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val VIEW_TYPE_MESSAGE_SENT = 1
        val VIEW_TYPE_MESSAGE_RECEIVED = 2

        lateinit var mMessageList: ArrayList<BaseMessage>
        lateinit var mChannel: OpenChannel

        init {
            mMessageList = ArrayList()
            mChannel = channel
            refresh()
        }

        // 가져다 씀 불분명함.
        fun refresh() {
            mChannel.getNextMessagesById(java.lang.Long.MAX_VALUE, true, 30, true,
                BaseChannel.MessageTypeFilter.USER, null, BaseChannel.GetMessagesHandler { list, e ->
                    if (e != null) {
                        e.printStackTrace()
                        return@GetMessagesHandler
                    }
                    mMessageList = list as java.util.ArrayList<BaseMessage>

                    notifyDataSetChanged()
                })
        }

        fun loadPreviousMessages() {
            val lastTimestamp = mMessageList[mMessageList.size - 1].createdAt
            mChannel.getNextMessagesById(lastTimestamp, false, 30, true,
                BaseChannel.MessageTypeFilter.USER, null, BaseChannel.GetMessagesHandler { list, e ->
                    if (e != null) {
                        e.printStackTrace()
                        return@GetMessagesHandler
                    }
                    mMessageList.addAll(list)

                    notifyDataSetChanged()
                })
        }

        // Appends a new message to the beginning of the message list.
        fun appendMessage(message: UserMessage) {
            mMessageList.add(0, message)
            notifyDataSetChanged()
        }

        fun sendMessage(message: String) {
            mChannel.sendUserMessage(message, BaseChannel.SendUserMessageHandler { userMessage, e ->
                if (e != null) {
                    e.printStackTrace();
                    return@SendUserMessageHandler
                }

                mMessageList.add(0, userMessage)
                notifyDataSetChanged()
                mRecyclerView.scrollToPosition(0)
            })
        }

        override fun getItemViewType(position: Int): Int {
            val message = mMessageList[position] as UserMessage

            return if (message.sender.userId == SendBird.getCurrentUser().userId) {
                // If the current user is the sender of the message
                VIEW_TYPE_MESSAGE_SENT
            } else {
                // If some other user sent the message
                VIEW_TYPE_MESSAGE_RECEIVED
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view: View

            if (viewType == VIEW_TYPE_MESSAGE_SENT) {
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false)
                return ReceivedMessageHolder(view, 0)
            } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
                view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false)
                return ReceivedMessageHolder(view, 1)
            }

            view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message_received, parent, false)
            return return ReceivedMessageHolder(view, 0)
        }

        // Passes the message object to a ViewHolder so that the contents can be bound to UI.
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val message = mMessageList[position] as UserMessage

            when (holder.itemViewType) {
                VIEW_TYPE_MESSAGE_SENT -> (holder as ReceivedMessageHolder).bind(message)
                VIEW_TYPE_MESSAGE_RECEIVED -> (holder as ReceivedMessageHolder).bind(message)
            }
        }

        override fun getItemCount(): Int {
            return mMessageList.size
        }

//        // Messages sent by me do not display a profile image or nickname.
//        private inner class SentMessageHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            internal var messageText: TextView
//            internal var timeText: TextView
//
//            init {
//
//                messageText = itemView.findViewById(R.id.text_message_body) as TextView
//                timeText = itemView.findViewById(R.id.text_message_time) as TextView
//            }
//
//            internal fun bind(message: UserMessage) {
//                messageText.text = message.message
//            }
//        }

        // Messages sent by others display a profile image and nickname.
        private inner class ReceivedMessageHolder internal constructor(itemView: View, val flag: Int) : RecyclerView.ViewHolder(itemView) {
            internal var messageText: TextView
            internal var timeText: TextView
            internal var nameText: TextView
            internal var profileImage: ImageView

            init {
                messageText = itemView.findViewById(R.id.text_message_body) as TextView
                timeText = itemView.findViewById(R.id.text_message_time) as TextView
                nameText = itemView.findViewById(R.id.text_message_name) as TextView
                profileImage = itemView.findViewById(R.id.image_message_profile) as ImageView
            }

            internal fun bind(message: UserMessage) {
                messageText.text = message.message
                if (flag == 0) {
                    nameText.text = "나"
                } else {
                    nameText.text = message.sender.nickname
                }

            }
        }


    }
}