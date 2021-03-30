<?php

namespace App\Form;

use App\Entity\Participant;
use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
class ParticipantType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
        ->add('typeParticipant', ChoiceType::class, array(
            'choices' => array(
                'Stagiaire' => 'Stagiaire',
                'Employé' => 'Employé',
                'Autre ' => 'Autre ',
            ),
        ))
            
            ->add('Nom')
            ->add('prenom')
            ->add('mail')
            ->add('idEvenement',EntityType::class, [
            'class'=>Evenement::class,
            'choice_label'=>'nom'

        ])
        ->add('captchaCode', CaptchaType::class, array(
            'captchaConfig' => 'ExampleCaptchaUserRegistration',
            'constraints' => [
                new ValidCaptcha([
                    'message' => 'Invalid captcha, please try again',
                ]),
            ],
        ))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Participant::class,
        ]);
    }
}
