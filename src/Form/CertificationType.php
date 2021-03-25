<?php

namespace App\Form;

use App\Entity\Certification;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;






use App\Entity\Entreprise;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;

use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class CertificationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Type')
            ->add('DatePassage')
            ->add('Prix')
            ->add('Description')
            ->add('Nom')
            ->add('Entreprise', EntityType::class, [
                'class'=>Entreprise::class,
                'choice_label'=>'nom',
                
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
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Certification::class,
        ]);
    }
}
