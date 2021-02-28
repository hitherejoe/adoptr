package co.joebirch.adoptr

object DogFactory {

    fun dogs(): List<Dog> {
        return listOf(
            elfin(),
            honey(),
            leo(),
            max(),
            ollie(),
            ringo(),
            scooby(),
            smurf()
        )
    }

    fun elfin(): Dog {
        return Dog(
            "elfin",
            "Elfin",
            "4 years",
            "Female",
            "Whippet",
            "https://www.raystede.org/pet-profile/21526",
            R.drawable.elfin,
            "Elfin has not had the best start in life, she is very nervous, lacks confidence, and is a sensitive, generally anxious girl. Will need a quiet, gentle, calm home, could possibly live with teenagers. Will need to live and be walked in quieter areas. Can be worried by some dogs, may be best as only dog in the home. Could not live with cats or small furries, will chase. Will need long term support to help continue to build her confidence up.",
            true,
            listOf(DogAttributes.CHILDREN, DogAttributes.DOGS),
            false
        )
    }

    fun honey(): Dog {
        return Dog(
            "honey",
            "Honey",
            "1 year",
            "Female",
            "Golden Retriever",
            "https://www.raystede.org/pet-profile/21200",
            R.drawable.honey,
            "Honey requires an experienced, active home as she has more complex behavioural support needs. We have received a lot of interest in Honey due to her breed type however she will require a home that matches for her specific, complex needs. \n \nHoney will need an experienced home to continue with her confidence building and behaviour modification work. Honey is currently receiving extra support generally, she hopes to start looking for a new home soon. She lacks confidence and is generally very nervous, as her anxiety builds she can become reactive (towards people, dogs and other animals) so she will need a home that will understand she needs time and lots of patience to adjust (several months). \n" +
                " \nHoney is very worried by strangers. She needs a home with knowledge and understanding of dog behaviour to continue building her confidence, long term commitment to management and training is required so patience is a must. Honey needs an quieter ADULT ONLY home. She needs to be walked in quieter, more rural areas. She is a very busy dog so will need a home that can provide mental stimulation as well as physical activity. \n" +
                " \nHoney does not like other dogs and needs to be the only dog in the home. She can not live with cats or small animals. Potential adopters should be aware that she will need several meetings over a period of time to get to know people, so will need to live close enough to the centre to be able to do these visits. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East.",
            false,
            listOf(DogAttributes.CHILDREN),
            false
        )
    }

    fun leo(): Dog {
        return Dog(
            "leo",
            "Leo",
            "4 years",
            "Male",
            "Staff Bull",
            "https://www.raystede.org/pet-profile/21257",
            R.drawable.leo,
            "Hi there, my name is Leo and I'm a sensitive soul who just needs some guidance in life and help redirecting my boisterous energy into fun things like agility and trick training. Plus a good massage wouldn't go amiss! Leo needs an experienced home that are very familiar with staffies and their enthusiastic nature. He acts like a giant puppy and bonds closely to those he trusts and knows and is very eager to learn however he is a powerful dog and generally boisterous. \n\nLeo is very worried by other dogs and can be reactive through fear or chew on the lead. He will need to be the only dog in the home and owners will need to have a strong understanding of dog behaviour and communication. He could live with older teenagers that can be involved with his training. Leo cannot live with cats. He loves to climb and explore on walks and has shown great enthusiasm for agility and eagerness to wait on platforms focusing on his handler. \n\nLeo will need a quiet home environment with a private, secure garden to explore. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East.",
            false,
            listOf(DogAttributes.CHILDREN, DogAttributes.SMALL_ANIMALS),
            false
        )
    }

    fun max(): Dog {
        return Dog(
            "max",
            "Max",
            "3 years",
            "Male",
            "GSD X Whippet",
            "https://www.raystede.org/pet-profile/21567",
            R.drawable.max,
            "Max is a German Shepherd cross lurcher, he is a LARGE dog and STRONG on-lead and generally very BOISTEROUS, a fun loving handful! Max is very friendly however requires commitment to full training as he lacks focus which can make him difficult to control. He could possibly live with confident older children who can cope with his size and strength. \n\nMax can be very boisterous with other dogs and frustrated to greet, he needs on-going training with this. Max may possibly live with confident, robust medium to larger sized dog however this would require careful consideration and an experienced home to manage behaviour in the home. He may possibly be able to live with a VERY confident cat that is well used to dogs and if applicants have previous experience of training dogs around cats. \n\nMax requires a home with the space for him and good sized, secure garden he can race around in due to high energy levels. Max is not used to travelling and can be a fidget, he is not used to being left alone and will be vocal (bark/ howl) so may have some separation issues to work on over time. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East",
            false,
            listOf(DogAttributes.DOGS, DogAttributes.CHILDREN),
            false
        )
    }

    fun ollie(): Dog {
        return Dog(
            "ollie",
            "Ollie",
            "1 years",
            "Male",
            "Border Collie",
            "https://www.raystede.org/pet-profile/21217",
            R.drawable.ollie,
            "Hi my name is Ollie and I'm looking for someone who understands my collie traits and can help me become the super dog I know I can be! Ollie will require a home with vast experience of training working collie types, he has strong chase drive to movement, is vigilant to noise and is exuberant, physically and mentally. Will require an adult only home, walks in areas away from traffic (sound sensitive to the noise). \n\nPlenty of physical activity as high energy, must combine with mental stimulation as busy mind. Needs help and guidance to be more social around/meeting with dogs, can be reactive when on lead, advise not to live with. Not suited to live with cats, birds or small furries. Will not cope living in a town setting. House trained, used to travelling in the car, not used to being left, will howl. The kennel team are currently giving Ollie extra support to prepare him for his new home. Ollie needs a quiet household with access to enclosed gardens. \n\nPotential adopters will need to bear in mind they may possibly have to visit the centre several times, to get to know him. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East.",
            false,
            emptyList(),
            false
        )
    }

    fun ringo(): Dog {
        return Dog(
            "ringo",
            "Ringo",
            "4 years",
            "Male",
            "Shar-Pei",
            "https://www.raystede.org/pet-profile/21270",
            R.drawable.ringo,
            "Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East. Ringo is a sensitive boy and will require a very experienced home that have had sharpeis previously. He will need to be the only pet in the home with ADULTs only, with no visiting children. \n\nHe is very cautious and vocal of new people initially. Bonds closely to one person and is then very playful and enjoys the odd massage. Worried by handling if not bonded with handler. Cautious of other dogs, would rather avoid, will growl or become vocal if they come into his space. Will require on-going medical care for his skin and bronchial congestion.",
            false,
            emptyList(),
            false
        )
    }

    fun scooby(): Dog {
        return Dog(
            "scooby",
            "Scooby",
            "13 years",
            "Male",
            "Border Collie X Lab",
            "https://www.raystede.org/pet-profile/21548",
            R.drawable.scooby,
            "New arrival awaiting assessment, further details will follow. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East. No previous history available. Scooby is a friendly dog, very active and playful still, loves football and tennis balls. \n\nVery excitable when going out for walks initially and will grab lead and shake it so needs handlers able to manage this excitable behaviour, he is surprisingly strong! Adult only home, can be cautious of some dogs, to be only dog in home, no cats. Unknown if house trained (probably?) or how travels (may be vocal?). Seems anxious if alone, prefers lots of company and being involved in everything. \n\nEye sight and hearing is very poor, though he seems able to cope fine, consider areas walked as will be worried if there is a lot of activity or noises that may startle him. Joints sore so requires supplements and may soon require on-going pain medication to manage his comfort levels so consider costs. Will require his own garden to explore and enjoy, not suited to properties with lots of stairs he will need to navigate. Further details to follow, applications from homes in South East only being considered.",
            false,
            listOf(DogAttributes.SMALL_ANIMALS),
            false
        )
    }

    fun smurf(): Dog {
        return Dog(
            "smurf",
            "Smurf",
            "5 months",
            "Male",
            "Lurcher",
            "https://www.raystede.org/pet-profile/21590",
            R.drawable.smurf,
            "New arrival awaiting assessment, further details will follow. Please note our usual rehoming procedures have been impacted at present due to restrictions, in addition we are only currently considering applicants based in the South East. Smurf has not had a positive start to life and will require strong commitment to full training (including how to walk on-lead along with all basic cue responses and general confidence building). \n\nHe has started to grow in confidence and his bouncy puppy character is building. Will be large size so consider beinag able to accommodate a large, strong, bouncy, energetic dog. Will need home with own secure garden. Could live with confident older children who are used to big, boisterous dogs. Could live with another dog if they are robust and able to cope with his boisterous play. \n\nNot tested with cats, due to breed type would only consider testing with cats if home has dog-confident cat and people are used to doing careful introductions and training. Not fully house trained, may be vocal and destructive if left alone? Travels OK. Application enquiries being accepted for homes in the South East only, further details will follow.",
            false,
            listOf(DogAttributes.DOGS, DogAttributes.CHILDREN, DogAttributes.SMALL_ANIMALS),
            false
        )
    }
}