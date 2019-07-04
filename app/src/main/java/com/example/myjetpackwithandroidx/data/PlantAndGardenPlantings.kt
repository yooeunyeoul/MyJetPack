package com.example.myjetpackwithandroidx.data

import androidx.room.Embedded
import androidx.room.Relation


/**
 * 컬럼의 부분집합 반환하기
개발자는 대부분 몇몇 필드만 엔티티로 부터 얻으려고 할겁니다.
예를 들면 사용자의 모든정보를 다 보여주기보다는 성이나 이름같은 정보만 말이죠. 앱내의 UI에서 몇몇의 컬럼만 가져오는 것만으로 리소스사용을 줄일수 있습니다.
그리고 쿼리 하는 시간도 단축되겠죠.
Room은 Query할때 반환값이 컬럼들의 부분집합인 이상 어떠한 자바기반의 오브젝트도 리턴할 수 있도록 허용하고 있습니다.
예를들면 POJO를 만들고 사용자의 성과 이름만 받는 클래스를 만들수도 있습니다.
 */
class PlantAndGardenPlantings {

    //https://medium.com/@magdamiu/android-room-persistence-library-relations-75bbe02e8522 참고

    // TODO 이게뭐지
    // 외래키 대신에 사용할 수 있음 Plant 와 GardenPlanting 을 연결 시키기 위해 해당 클래스는 존재함
    // 때로는 여러 필드를 포함하고 있는 어떠한 객체를 POJO 나 Entity 를 통해 표현하고 싶을때가 있다.
    // 이런경우에는 @Embeded 애노테이션을 사용하여 테이블 내의 하위 필드로 분해 할 객체를 나타낼 수 있습니다.
    // 그런 다음 다른 각가의 컬럼과 마찬가지로 포함된 필드를 쿼리 할 수 있다.
    // Note:Embeded 필드는 또 다른 Embeded 필드를 포함할 수 있다.

    @Embedded
    lateinit var plant: Plant

    // TODO 이건 또 뭐지
    @Relation(parentColumn = "id", entityColumn = "plant_id")
    var gardenPlantings: List<GardenPlanting> = arrayListOf()
}