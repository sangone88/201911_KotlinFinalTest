package com.tjoeun.a201911_kotlinfinaltest

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : BaseActivity() {
    
    var mHandler = Handler()
    var isNowBuying = false

//    누적 사용금액
    var userdMoney = 0L
//    누적 당첨금액
    var luckyMoney = 0L

//    1~5등 당첨
    var firstRankCount = 0
    var secondRankCount = 0
    var thirdRankCount = 0
    var fourthRankCount = 0
    var fifthRankCount = 0
    var wrongCount = 0

//    정석 당첨번호 6개 저장 배열
    var lottoNumArrayList = ArrayList<Int>()
//    보너스 번호
    var bonusNum = 0
    var thisWeekLottoNumTextViewArrayList = ArrayList<TextView>()
    var myNumArrayList = ArrayList<Int>()
    var myTextViewArrayList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        autoLottoBtn.setOnClickListener {
            if (!isNowBuying) {
                doLottoLoop()
                isNowBuying = true
                autoLottoBtn.text = "구매 중단"
            }
            else {
//                반복 중단시키기.
                stopLottoLoop()
                isNowBuying = false
                autoLottoBtn.text = "자동 구매 재개"
            }

        }

        buyOneLottoBtn.setOnClickListener {
//            숫자를 랜덤으로 6개 생성. 1~45 / 중복 안됨.
            setThisWeekLottoNum()
            checkLottoRank()
            userdMoney += 1000
            usedMoneyTxt.text = String.format("사용금액 : %,d원", userdMoney)
        }
    }

//    당첨 경과를 체크. 몇등인지 확인
    fun checkLottoRank() {

//        6개 : 1등 => 20억원.
//        5개 : 3등 => 150만원.
//        5개
//            보너스번호가 맞으면 : 2등 => 65백만원
//            보너스번호 못맞췄으면 : 3등 => 150만원.
//        4개 : 4등 => 5만원
//        3개 : 5등 => 5천원
//    그 이하 : 꽝 : 0원

        var correctCount = 0
        for (myNum in myNumArrayList) {
            for (thisWeekNum in lottoNumArrayList) {
                if (myNum == thisWeekNum) {
                    correctCount++
                }
            }
        }

        if (correctCount == 6) {
//            Toast.makeText(mContext, "1등 당첨!", Toast.LENGTH_SHORT).show()
            luckyMoney += 2000000000
            firstRankCount++
        }
        else if (correctCount == 5) {
//            Toast.makeText(mContext, "3등 당첨!", Toast.LENGTH_SHORT).show()

            var isSecondRank = false
            for (num in myNumArrayList) {
                if (num == bonusNum) {
                    isSecondRank = true
                    break
                }
            }

            if (isSecondRank) {
                luckyMoney += 65000000
                secondRankCount++
            }
            else{
                luckyMoney += 1500000
                thirdRankCount++
            }

        }
        else if (correctCount == 4) {
//            Toast.makeText(mContext, "4등 당첨!", Toast.LENGTH_SHORT).show()
            luckyMoney += 50000
            fourthRankCount++
        }
        else if (correctCount == 3) {
//            Toast.makeText(mContext, "5등 당첨!", Toast.LENGTH_SHORT).show()
            userdMoney -= 5000
            fifthRankCount++
        }
        else {
//            Toast.makeText(mContext, "꽝입니다.", Toast.LENGTH_SHORT).show()
            luckyMoney += 0
            wrongCount++
        }
        luckyMoneyTxt.text = String.format("누적 당첨 금액 : %,d원", luckyMoney)
        firstRankCountTxt.text = String.format("1등 당첨 : %,d회", firstRankCount)
        secondRankCountTxt.text = String.format("2등 당첨 : %,d회", secondRankCount)
        thirdRankCountTxt.text = String.format("3등 당첨 : %,d회", thirdRankCount)
        fourthRankCountTxt.text = String.format("4등 당첨 : %,d회", fourthRankCount)
        fifthRankCountTxt.text = String.format("5등 당첨 : %,d회", fifthRankCount)
        wrongRankCountTxt.text = String.format("낙첨 횟수 : %,d회", wrongCount)

    }

    var lottoRunnable = object : Runnable {
        override fun run() {
            if (userdMoney < 100000000) {
                setThisWeekLottoNum()
                checkLottoRank()
                userdMoney += 1000
                usedMoneyTxt.text = String.format("사용금액 : %,d원", userdMoney)
                doLottoLoop()
            }
            else{
                runOnUiThread {
                    Toast.makeText(mContext, "로또 구매를 종료합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun doLottoLoop() {
        mHandler.post(lottoRunnable)
    }

    fun stopLottoLoop() {
        mHandler.removeCallbacks(lottoRunnable)
    }

    fun setThisWeekLottoNum() {

//        당첨번호는 모두 날리고 다시 뽑자.
        lottoNumArrayList.clear()

//        6개의 텍스트뷰에 들어갈 당첨번호를 뽑아내는 반복문
        for (lottoNumTxt in thisWeekLottoNumTextViewArrayList) {

//            선정된 랜덤값이 들어갈변수
            var randomNum = 0

//            몇번을 반복해야 중복을 피할지 알수 없다.
//            반복문 : 횟수가 명확하면 for, 언제까지 돌려야할지 모르면 while(true) => if (조건) brack)
            while (true) {

//                1~45 사이의 랜덤값을 뽑아서 변수에 임지 저장.
                randomNum = (Math.random() * 45 + 1).toInt()

//                일단 중복 되지 않는다. (괜찮다)라고 전제하고 검사 시작
                var isDuplOk = true
//                지금 만든 랜덤번호와 꺼내본 당첨번호가 같은가?
                for (num in lottoNumArrayList) {

                    if (num == randomNum) {
//                        중복되는 숫자를 발견!
//                        더이상 중복검사를 통과할 수 없다.
                        isDuplOk = false
                        break
                    }
                }
//                중복검사를 통과했는지 확인
                if (isDuplOk) {
//                    만약 통과했다면 당첨번호롤 넣어주자.
                    lottoNumArrayList.add(randomNum)
//                    올바른 번호를 뽑았으니 무한반복을 탈출.
                    break
                }
            }
//            순서가 제먹대로여서 보기 안좋다.
//            lottoNumTxt.text = randomNum.toString()
        }
//        당첨번호 6개를 작은 숫자부터 큰 숫자 순서대로 (정렬)!
        Collections.sort(lottoNumArrayList)

//        보너스번호를 추가로 뽑자. 중복을 피해야함. => 몇번이나 뽑아야 중복이 아닐지 알수 없다.
        while (true) {
            var tempRandomNum = (Math.random() * 45 + 1).toInt()
            var isDuplOk = true

            for (num in lottoNumArrayList) {
                if (tempRandomNum == num) {
//                    중복 발견! 다시 뽑아야함.
                    isDuplOk = false
                    break
                }
            }
            if (isDuplOk) {
                bonusNum = tempRandomNum
                break
            }

        }


//        6개의 텍스트뷰 / 당첨번호를 뽑아내서 연결.
        for (i in 0..thisWeekLottoNumTextViewArrayList.size -1) {
            var numTxt = thisWeekLottoNumTextViewArrayList.get(i)
            var number = lottoNumArrayList.get(i)

            numTxt.text = number.toString()
        }

        bonusNumTxt.text = bonusNum.toString()

    }

    override fun setValues() {
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt1)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt2)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt3)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt4)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt5)
        thisWeekLottoNumTextViewArrayList.add(lottoNumTxt6)

        myTextViewArrayList.add(myLottoNumTxt1)
        myTextViewArrayList.add(myLottoNumTxt2)
        myTextViewArrayList.add(myLottoNumTxt3)
        myTextViewArrayList.add(myLottoNumTxt4)
        myTextViewArrayList.add(myLottoNumTxt5)
        myTextViewArrayList.add(myLottoNumTxt6)

        for (myTv in myTextViewArrayList) {
            myNumArrayList.add(myTv.text.toString().toInt())
        }
    }

}
